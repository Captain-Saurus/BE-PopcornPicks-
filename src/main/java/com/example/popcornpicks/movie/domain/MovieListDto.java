package com.example.popcornpicks.movie.domain;


import com.example.popcornpicks.common.domain.Page;
import com.example.popcornpicks.movie.feign.domain.Movie;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MovieListDto {

    @Getter
    @AllArgsConstructor
    public static class Response{
        private Page page;

        @Nullable
        private Movie.Response movieListResult;
    }

}
