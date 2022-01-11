package com.nhn.nhnsoft.jobray.userservice.global.exception.common;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private Integer code;
    public BadRequestException(String msg) {
        super(msg);
    }
    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public BadRequestException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }
}
