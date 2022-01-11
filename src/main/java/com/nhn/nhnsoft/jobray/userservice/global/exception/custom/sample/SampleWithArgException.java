package com.nhn.nhnsoft.jobray.userservice.global.exception.custom.sample;

import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import com.nhn.nhnsoft.jobray.userservice.global.exception.custom.BusinessException;

import java.util.Locale;

public class SampleWithArgException extends BusinessException {
    private static final ErrorCode ERROR_CODE = ErrorCode.SAMPLE_WITH_ARG_EXCEPTION;

    // 기본 생성자 (Locale = Session 값에 따름 = LocaleContextHolder)
    public SampleWithArgException() {
        super(ERROR_CODE);
    }

    // 예외 메시지 Locale 직접 지정
    public SampleWithArgException(Locale locale) {
        super(ERROR_CODE, locale);
    }

    //  arguments 전달 ( error.msg.xxx.xxx = bla..bal.. {0}, bla.. {1} bla. }
    public SampleWithArgException(String[] strings) {
        super(ERROR_CODE, strings);
    }

    // arguments + Locale 전달
    public SampleWithArgException(String[] strings, Locale locale) {
        super(ERROR_CODE, strings, locale);
    }
}
