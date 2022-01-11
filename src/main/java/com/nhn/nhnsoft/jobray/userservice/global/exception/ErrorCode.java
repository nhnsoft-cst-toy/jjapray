package com.nhn.nhnsoft.jobray.userservice.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // SAMPLE
    SAMPLE_NORMAL_EXCEPTION(HttpStatus.BAD_REQUEST, 9990, "error.msg.sample.normal"),
    SAMPLE_WITH_ARG_EXCEPTION(HttpStatus.CONFLICT, 9991, "error.msg.sample.with-arg"),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String messageCode;

    ErrorCode(HttpStatus httpStatus, int code, String messageCode) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public int getHttpStatusCode() {
        return this.httpStatus.value();
    }
}
