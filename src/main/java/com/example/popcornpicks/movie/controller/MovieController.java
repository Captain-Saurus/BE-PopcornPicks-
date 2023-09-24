package com.example.popcornpicks.movie.controller;

import com.example.popcornpicks.common.ApiResponse;
import com.example.popcornpicks.movie.domain.DailyBoxOffice;
import com.example.popcornpicks.movie.domain.KobisCommonResponse;
import com.example.popcornpicks.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/daily_box/list")
    public ApiResponse<KobisCommonResponse<DailyBoxOffice.Response>> getDailyBoxList() {
        return ApiResponse.createOk(movieService.getDailyBoxOfficeList());
    }

}
