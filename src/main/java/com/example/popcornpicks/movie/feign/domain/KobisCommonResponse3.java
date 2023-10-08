package com.example.popcornpicks.movie.feign.domain;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

// 영화 상세정보
@Data
public class KobisCommonResponse3 {

    @Nullable private MovieInfo.Response movieInfoResult;


    @Nullable private Error faltInfo;

    @Getter
    @RequiredArgsConstructor
    public static class Error {
        private final String message;
        private final String errorCode;
    }

    public boolean isSuccess() {
        return Objects.nonNull(movieInfoResult) && Objects.isNull(faltInfo);
    }
}
