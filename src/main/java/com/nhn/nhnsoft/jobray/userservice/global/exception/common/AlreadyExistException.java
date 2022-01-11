package com.nhn.nhnsoft.jobray.userservice.global.exception.common;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String msg) {
        super(msg);
    }
}
