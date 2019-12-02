package com.skrshop.securitycore.validate.code;

import org.springframework.security.core.AuthenticationException;


public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
