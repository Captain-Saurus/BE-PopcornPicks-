package com.example.popcornpicks.common.exception;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import lombok.Getter;

@Getter

public class InvalidParameterException extends CommonException{

    public InvalidParameterException(String message) {
        super(HttpStatusCode.INVALID_PARAMETER, message);
    }
}
