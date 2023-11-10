package com.example.popcornpicks.movie.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DailyBoxOfficeMovieDto {
    private final int rank;
    private final String movieImageUrl;
    private final String movieName;
    private final String rankAdjustment;
}
