package com.example.popcornpicks.movie.service;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import com.example.popcornpicks.common.enums.Yn;
import com.example.popcornpicks.common.exception.CommonException;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeTrailerDto;
import com.example.popcornpicks.movie.feign.KmdbClient;
import com.example.popcornpicks.movie.feign.KobisClient;
import com.example.popcornpicks.movie.feign.YoutubeClient;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOfficeDto;
import com.example.popcornpicks.movie.feign.domain.KmdbDto;
import com.example.popcornpicks.movie.feign.domain.KmdbProperty;
import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.feign.domain.property.KobisProperty;
import com.example.popcornpicks.movie.feign.domain.property.YoutubeProperty;
import com.example.popcornpicks.movie.feign.domain.property.YoutubeSearchDto;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private final KobisClient movieInfoClient;
    private final KmdbClient kmdbClient;
    private final YoutubeClient youtubeClient;

    private final KmdbProperty kmdbProperty;
    private final YoutubeProperty youtubeProperty;
    private final KobisProperty kobisProperty;

    private final ObjectMapper objectMapper;

    private static final int SHOW_MOVIE_COUNT = 5;
    private static final int YOUTUBE_VIDEO_COUNT = 1;

    public List<DailyBoxOfficeMovieDto> getDailyBoxOfficeList() {
        // 한국영화진흥원(kofic-api)를 통해 박스오피스 영화 리스트 받아오기
        DailyBoxOfficeDto.Response response = getDailyBoxOfficeMoviesFromKofic();

        // kdbd-api를 통해 영화 poster 이미지 받아오기
        return getDailyBoxOfficeMoviePostFromKmdb(response);
    }

    public List<DailyBoxOfficeTrailerDto> getTrailers() {
        List<DailyBoxOfficeTrailerDto> trailerDtos = new ArrayList<>();

        // 한국영화진흥원(kofic-api)를 통해 박스오피스 영화 리스트 받아오기
        DailyBoxOfficeDto.Response boxOfficeMoviesFromKofic = getDailyBoxOfficeMoviesFromKofic();
        List<String> top5DailyBoxOfficeMovieNameList = boxOfficeMoviesFromKofic.getTop5DailyBoxOfficeMovieNameList();

        // youtube-api를 통해 예고편 영상 ID 추출
        for (String movieName : top5DailyBoxOfficeMovieNameList) {
            YoutubeSearchDto.Response res = youtubeClient.getTrailerMovieId(
                    new YoutubeSearchDto.Request(youtubeProperty
                            , YOUTUBE_VIDEO_COUNT
                            , movieName
                    )
            );
            trailerDtos.add(new DailyBoxOfficeTrailerDto(movieName, res.extractVideoId()));
        }
        return trailerDtos;
    }

    private DailyBoxOfficeDto.Response getDailyBoxOfficeMoviesFromKofic() {
        return requestDailyBoxOffice(
                new DailyBoxOfficeDto.Request(
                        kobisProperty.getKey()
                        , LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")
                )
                ));
    }

    private List<DailyBoxOfficeMovieDto> getDailyBoxOfficeMoviePostFromKmdb(DailyBoxOfficeDto.Response response) {
        List<DailyBoxOfficeMovieDto> dailyBoxOfficeMovieDtos = new ArrayList<>();

        for (DailyBoxOfficeDto.Detail detail : response.getTopNDailyBoxOfficeList(SHOW_MOVIE_COUNT)) {
            String kmdbRes = kmdbClient.getMovieDetailForPoster(kmdbProperty.getServiceKey()
                    , kmdbProperty.getCollection()
                    , Yn.Y.name()
                    , detail.getMovieNm()
                    , detail.getOpenDt()
            );
            dailyBoxOfficeMovieDtos.add(detail.toResponse(getPostsUrlFromResponse(kmdbRes)));
        }

        return dailyBoxOfficeMovieDtos;
    }

    private String getPostsUrlFromResponse(String kmdbRes) {
        try {
            KmdbDto.Response response = objectMapper.readValue(kmdbRes, KmdbDto.Response.class);
            return response.extractPostUrl();
        } catch (JacksonException e) {
            log.error("kmdb 응답값 파싱중 에러 발생: {}", e.getMessage());
            return null;
        }
    }

    private DailyBoxOfficeDto.Response requestDailyBoxOffice(DailyBoxOfficeDto.Request request) {
        KobisCommonResponse<DailyBoxOfficeDto.Response> response = movieInfoClient.getDailyBoxOfficeData(request);

        if (response.isSuccess() == false) {
            throw new CommonException(HttpStatusCode.INTERNAL_SERVER_ERROR
                    , response.getFaltInfo().getMessage());
        }

        return response.getBoxOfficeResult();
    }

}
