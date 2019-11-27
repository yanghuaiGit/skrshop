package com.skrshop.oauthcenter.security.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ValidateCode generateCode(HttpServletRequest httpServletRequest);
}
