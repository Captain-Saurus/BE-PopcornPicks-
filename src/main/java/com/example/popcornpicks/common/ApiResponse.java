package com.example.popcornpicks.common;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private Meta meta;
    private T data;

    @AllArgsConstructor
    @Getter
    public static class Meta {
        private int status;
        private String code;
        private String message;
        private LocalDateTime createdAt;
    }

    private ApiResponse(final T data, final HttpStatusCode code) {
        this.data = data;
        this.meta = new Meta(
                code.getStatus(),
                code.getCode(),
                code.getDefaultMessage(),
                LocalDateTime.now()
        );
    }

    private ApiResponse(final HttpStatusCode code, final String message, final LocalDateTime createdAt) {
        this.meta = new Meta(
                code.getStatus(),
                code.getCode(),
                message,
                createdAt
        );
    }

    /**
     * API 성공 시, 공통 Response 정의
     * @param data
     * @return
     * @param <T>
     */
    public static<T> ApiResponse<T> createOk(final T data) {
        return new ApiResponse<>(data, HttpStatusCode.OK);
    }

    public static<T> ApiResponse<T> createEmptyBody() {
        return createOk(null);
    }

    public static<T> ApiResponse<T> createError(final HttpStatusCode code, final String message) {
        return new ApiResponse<>(code, message, LocalDateTime.now());
    }

}
