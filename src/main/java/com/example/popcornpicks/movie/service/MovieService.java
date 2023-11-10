package com.example.popcornpicks.movie.service;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import com.example.popcornpicks.common.enums.Yn;
import com.example.popcornpicks.common.exception.CommonException;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.feign.KmdbClient;
import com.example.popcornpicks.movie.feign.MovieInfoClient;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOfficeDto;
import com.example.popcornpicks.movie.feign.domain.KmdbDto;
import com.example.popcornpicks.movie.feign.domain.KmdbProperty;
import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private final MovieInfoClient movieInfoClient;
    private final KmdbClient kmdbClient;

    private final KmdbProperty kmdbProperty;
    private final ObjectMapper objectMapper;

    @Value("${kobis.key}")
    private String kobisKey;
    private static final int SHOW_MOVIE_COUNT = 5;

    public List<DailyBoxOfficeMovieDto> getDailyBoxOfficeList() {
        DailyBoxOfficeDto.Request request = new DailyBoxOfficeDto.Request(
                kobisKey,
                LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        );
        DailyBoxOfficeDto.Response response = requestDailyBoxOffice(request);

        // kdbd-api 활용하여 영화 poster 이미지 받아오기
        return getDailyBoxOfficeMoviePostFromKmdb(response);
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
