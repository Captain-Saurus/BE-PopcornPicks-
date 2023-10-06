package com.example.popcornpicks.movie.service;

import com.example.popcornpicks.common.domain.Page;
import com.example.popcornpicks.common.enums.HttpStatusCode;
import com.example.popcornpicks.common.exception.CommonException;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.domain.MovieDto;
import com.example.popcornpicks.movie.domain.MovieListDto;
import com.example.popcornpicks.movie.feign.domain.*;
import com.example.popcornpicks.movie.feign.MovieInfoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieInfoClient movieInfoClient;

    @Value("${kobis.key}")
    private String kobisKey;

    // 일별 박스오피스
    public List<DailyBoxOfficeMovieDto> getDailyBoxOfficeList() {
        DailyBoxOffice.Request request = new DailyBoxOffice.Request(
                kobisKey,
                LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        );

        DailyBoxOffice.Response response = this.requestDailyBoxOffice(request);
        return response.makeDailyBoxOfficeMovieDto();
    }

    private DailyBoxOffice.Response requestDailyBoxOffice(DailyBoxOffice.Request request) {
        KobisCommonResponse<DailyBoxOffice.Response> response = movieInfoClient.getDailyBoxOfficeData(request);

        if (response.isSuccess() == false) {
            throw new CommonException(HttpStatusCode.INTERNAL_SERVER_ERROR
                    , response.getFaltInfo().getMessage());
        }
        return response.getBoxOfficeResult();
    }


    // kobis 영화목록
    public MovieListDto.Response getMovie(Page page){
        Movie.Request request = new Movie.Request(
                kobisKey, Integer.toString(page.getPage()), Integer.toString(page.getSize())
        );

        MovieListDto.Response response = this.requestMovie(request);
        return response;
    }

    private MovieListDto.Response requestMovie(Movie.Request request){
        KobisCommonResponse2 response = movieInfoClient.getMovie(request);

        if(response.isSuccess() == false){
            throw new CommonException(HttpStatusCode.INTERNAL_SERVER_ERROR
                    , response.getFaltInfo().getMessage());
        }
        return new MovieListDto.Response(new Page(Integer.parseInt(request.getCurPage()),
                Integer.parseInt(request.getItemPerPage()), response.getMovieListResult().getTotCnt()), response.getMovieListResult());

    }
}
