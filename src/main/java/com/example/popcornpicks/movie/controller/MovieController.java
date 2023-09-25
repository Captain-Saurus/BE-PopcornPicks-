package com.example.popcornpicks.movie.controller;

import com.example.popcornpicks.common.ApiResponse;
import com.example.popcornpicks.movie.domain.DailyBoxOfficeMovieDto;
import com.example.popcornpicks.movie.feign.domain.DailyBoxOffice;
import com.example.popcornpicks.movie.feign.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/daily_box/list")
    public ApiResponse<List<DailyBoxOfficeMovieDto>> getDailyBoxList() {
        return ApiResponse.createOk(movieService.getDailyBoxOfficeList());
    }

}
