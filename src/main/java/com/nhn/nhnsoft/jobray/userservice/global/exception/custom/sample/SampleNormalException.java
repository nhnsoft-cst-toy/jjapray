package com.nhn.nhnsoft.jobray.userservice.global.exception.custom.sample;

import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import com.nhn.nhnsoft.jobray.userservice.global.exception.custom.BusinessException;

import java.util.Locale;

public class SampleNormalException extends BusinessException {
    private static final ErrorCode ERROR_CODE = ErrorCode.SAMPLE_NORMAL_EXCEPTION;

    // 기본 생성자 (Locale = Session 값에 따름 = LocaleContextHolder)
    public SampleNormalException() {
        super(ERROR_CODE);
    }

    // 예외 메시지 Locale 직접 지정
    public SampleNormalException(Locale locale) {
        super(ERROR_CODE, locale);
    }

    // error result 맵핑 테스트
    public SampleNormalException(Object errorResult) {
        super(ERROR_CODE, errorResult);
    }
}
