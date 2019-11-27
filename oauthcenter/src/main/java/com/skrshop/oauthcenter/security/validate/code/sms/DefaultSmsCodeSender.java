package com.skrshop.oauthcenter.security.validate.code.sms;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void sendCode(Long phoneNumber, String code) {
        log.info("手机号{} 发送验证码{}", phoneNumber, code);
    }
}
