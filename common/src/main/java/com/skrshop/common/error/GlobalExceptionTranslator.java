package com.skrshop.common.error;

import com.skrshop.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return BaseResponse
                .code(CommonResultCode.resultCodeFactory(CommonResultCode.PARAM_MISS,message))
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return BaseResponse
                .code(CommonResultCode.resultCodeFactory(CommonResultCode.PARAM_TYPE_ERROR,message))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .code(CommonResultCode.resultCodeFactory(CommonResultCode.PARAM_VALID_ERROR,message))
                .build();
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .code(CommonResultCode.resultCodeFactory(CommonResultCode.PARAM_BIND_ERROR,message))
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return BaseResponse
                .code(CommonResultCode.resultCodeFactory(CommonResultCode.PARAM_VALID_ERROR, message))
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse handleError(NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        return BaseResponse
                .code(CommonResultCode.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleError(HttpMessageNotReadableException e) {
        log.error("Message Not Readable", e);
        return BaseResponse
                .code(CommonResultCode.MSG_NOT_READABLE)
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleError(HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        return BaseResponse
                .code(CommonResultCode.METHOD_NOT_SUPPORTED)
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleError(HttpMediaTypeNotSupportedException e) {
        log.error("Media Type Not Supported", e);
        return BaseResponse
                .code(CommonResultCode.MEDIA_TYPE_NOT_SUPPORTED)
                .build();
    }

    @ExceptionHandler(SkrShopException.class)
    public BaseResponse handleError(SkrShopException e) {
        log.error("Service Exception{}", ToStringBuilder.reflectionToString(e.getResultCode(), ToStringStyle.DEFAULT_STYLE),e);
        return BaseResponse
                .code(e.getResultCode())
                .build();
    }


    @ExceptionHandler(Throwable.class)
    public BaseResponse handleError(Throwable e) {
        log.error("Internal Server Error", e);
        return BaseResponse
                .code(CommonResultCode.INTERNAL_SERVER_ERROR)
                .build();
    }
}
