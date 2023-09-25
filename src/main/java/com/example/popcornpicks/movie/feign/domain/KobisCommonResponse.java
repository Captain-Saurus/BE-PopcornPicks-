package com.example.popcornpicks.movie.domain;

import lombok.Data;

@Data
public class KobisCommonResponse<T> {
    private T boxOfficeResult;
}
