package com.skrshop.oauthcenter.security.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ImageCode generateCode(HttpServletRequest httpServletRequest);
}
