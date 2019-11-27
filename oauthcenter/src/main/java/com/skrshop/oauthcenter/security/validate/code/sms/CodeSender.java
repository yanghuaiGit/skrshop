package com.skrshop.oauthcenter.security.validate.code.sms;

import com.skrshop.oauthcenter.security.validate.code.ValidateCode;

public interface CodeSender {
    void sendCode(Long phoneNumber, ValidateCode code);
}
