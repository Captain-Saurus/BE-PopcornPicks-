package com.example.popcornpicks.movie.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class CommonResponse<T> {
    private T boxOfficeResult;
}
