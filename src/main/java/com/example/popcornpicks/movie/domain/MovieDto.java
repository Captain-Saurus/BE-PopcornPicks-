package com.example.popcornpicks.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDto {
    private final String movieCd;
    private final String movieNm;
    private final String openDt;
}
