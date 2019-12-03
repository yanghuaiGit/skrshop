package com.skrshop.securitycore.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 这里简单的实现默认的短信发送
 * TODO 需要接入真实的短信发送
 */
@Slf4j
public class SmsDefaultCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机{}发送手机验证码{}", mobile, code);
    }
}
