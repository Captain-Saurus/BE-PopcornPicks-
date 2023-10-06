package com.example.popcornpicks.movie.feign.domain;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
public class KobisCommonResponse2 {

    @Nullable private Movie.Response movieListResult;


    @Nullable private Error faltInfo;

    @Getter
    @RequiredArgsConstructor
    public static class Error {
        private final String message;
        private final String errorCode;
    }

    public boolean isSuccess() {
        return Objects.nonNull(movieListResult) && Objects.isNull(faltInfo);
    }
}
