package com.nhn.nhnsoft.jobray.userservice.global.exception.custom;


import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import lombok.Getter;

import java.util.Locale;

@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;
    private Object result;
    private Object[] messageArgArray;
    private Locale locale; // Locale 강제 지정 (적용 우선순위 : 최상)

    private String customMessage;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object result) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.result = result;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object result) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.result = result;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.locale = locale;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.locale = locale;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object[] messageArgArray) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object[] messageArgArray) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object[] messageArgArray, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.locale = locale;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object[] messageArgArray, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.locale = locale;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object result, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.result = result;
        this.locale = locale;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object result, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.result = result;
        this.locale = locale;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object[] messageArgArray, Object result) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.result = result;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object[] messageArgArray, Object result) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.result = result;
        this.customMessage = customMessage;
    }

    public BusinessException(ErrorCode errorCode, Object[] messageArgArray, Object result, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.result = result;
        this.locale = locale;
    }

    public BusinessException(ErrorCode errorCode, String customMessage, Object[] messageArgArray, Object result, Locale locale) {
        super(errorCode.getMessageCode());
        this.errorCode = errorCode;
        this.messageArgArray = messageArgArray;
        this.result = result;
        this.locale = locale;
        this.customMessage = customMessage;
    }

}
