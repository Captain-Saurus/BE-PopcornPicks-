package com.example.popcornpicks.movie.controller;

import com.example.popcornpicks.common.ApiResponse;
import com.example.popcornpicks.common.domain.Page;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.domain.MovieDto;
import com.example.popcornpicks.movie.domain.MovieListDto;
import com.example.popcornpicks.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // 일별 박스오피스
    @GetMapping("/daily_box/list")
    public ApiResponse<List<DailyBoxOfficeMovieDto>> getDailyBoxList() {
        return ApiResponse.createOk(movieService.getDailyBoxOfficeList());
    }

    // kobis 영화 조회
    @GetMapping("/movie_list")
    public ApiResponse<MovieListDto.Response> getMovie(
            @RequestParam("size") int size,
            @RequestParam("page") int page) {
        return ApiResponse.createOk(movieService.getMovie(new Page(page, size)));
    }


}
