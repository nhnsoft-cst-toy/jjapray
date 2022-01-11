package com.nhn.nhnsoft.jobray.userservice.infra.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FailToEncodeUrlException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FailToEncodeUrlException(String message) {
        super(message);
    }
}
