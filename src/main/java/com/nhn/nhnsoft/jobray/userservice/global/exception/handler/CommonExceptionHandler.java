package com.nhn.nhnsoft.jobray.userservice.global.exception.handler;

import com.nhn.nhnsoft.jobray.userservice.global.common.CareResponse;
import com.nhn.nhnsoft.jobray.userservice.global.exception.ErrorCode;
import com.nhn.nhnsoft.jobray.userservice.global.exception.common.*;
import com.nhn.nhnsoft.jobray.userservice.global.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(ex);
        String errMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> vl = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> {
                            String defaultMessage = f.getDefaultMessage();
                            return defaultMessage + " : field = " + f.getField();
                        }
                )
                .collect(Collectors.toList());
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(errMessage);
        response.setResult(vl);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<?> handleErrorStatus(ConstraintViolationException exception) {
        logger.warn("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    protected ResponseEntity<?> handleErrorStatus(AlreadyExistException exception) {
        logger.warn("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.ALREADY_REPORTED.value());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
    }

    @ExceptionHandler(value = {MethodNotAllowedException.class})
    protected ResponseEntity<?> handleErrorStatus(MethodNotAllowedException exception) {
        logger.warn("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {NotExistException.class})
    protected ResponseEntity<?> handleErrorStatus(NotExistException exception) {
        logger.warn("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<?> handleErrorStatus(BadRequestException exception) {
        logger.warn("", exception);
        CareResponse<Map<String, Object>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        if (exception.getCode() != null && exception.getCode() > 0) {
            Map<String, Object> m = new HashMap<>();
            m.put("code", exception.getCode());
            response.setResult(m);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<?> handleErrorStatus(IllegalArgumentException exception) {
        logger.warn("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    protected ResponseEntity<?> handleInternalServerErrorExceptionException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> handleException(Exception exception) {
        logger.error("", exception);
        CareResponse<List<String>> response = new CareResponse();
        response.setMessage(exception.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<?> handleBusinessException(BusinessException exception) {
        // Set locale
        Locale locale = exception.getLocale() != null ? exception.getLocale() : LocaleContextHolder.getLocale();

        // Set error code
        ErrorCode errorCode = exception.getErrorCode();

        // Set error message
        String errorMessage = exception.getCustomMessage() == null
                ? messageSource.getMessage(errorCode.getMessageCode(), exception.getMessageArgArray(), locale)
                : exception.getCustomMessage();
        logger.warn("Error Code=" + errorCode + ", Message=" + errorMessage, exception);

        // Set error Result
        Object result = exception.getResult() != null ? exception.getResult() : false;

        // Set exception response
        CareResponse<Object> response = new CareResponse();
        response.setMessage(errorMessage);
        response.setCode(errorCode.getCode());
        response.setResult(result);

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

}
