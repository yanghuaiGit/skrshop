package com.skrshop.oauthcenter.security.validate.code.sms;

public interface SmsCodeSender {
    void sendCode(Long phoneNumber, String code);
}
