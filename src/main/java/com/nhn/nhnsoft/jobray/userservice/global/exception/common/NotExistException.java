package com.nhn.nhnsoft.jobray.userservice.global.exception.common;

import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import com.nhn.nhnsoft.jobray.userservice.global.exception.custom.BusinessException;

public class NotExistException extends BusinessException {
    public NotExistException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotExistException(ErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }
}
