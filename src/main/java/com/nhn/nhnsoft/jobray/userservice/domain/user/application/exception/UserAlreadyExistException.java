package com.nhn.nhnsoft.jobray.userservice.domain.user.application.exception;

import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import com.nhn.nhnsoft.jobray.userservice.global.exception.custom.BusinessException;


public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXIST);
    }
}
