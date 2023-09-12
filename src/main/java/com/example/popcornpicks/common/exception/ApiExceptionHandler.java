package com.example.popcornpicks.common.exception;

import com.example.popcornpicks.common.ApiResponse;
import com.example.popcornpicks.common.enums.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handler(Exception e) {
        log.error(e.getMessage(), e);
        return ApiResponse.createError(
                HttpStatusCode.INTERNAL_SERVER_ERROR, e.getMessage()
        );
    }
}
