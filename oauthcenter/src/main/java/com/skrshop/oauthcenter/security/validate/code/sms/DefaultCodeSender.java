package com.skrshop.oauthcenter.security.validate.code.sms;


import com.skrshop.oauthcenter.security.validate.code.ValidateCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCodeSender implements CodeSender {
    @Override
    public void sendCode(Long phoneNumber, ValidateCode validateCode) {
        log.info("手机号{} 发送验证码{}", phoneNumber, validateCode.getCode());
    }
}
