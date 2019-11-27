package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.common.context.RequestHolder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsCodeProcessor extends AbsTractValidateCodeProcessor {

    private ValidateCodeGenerator validateCodeGenerator;

    public SmsCodeProcessor(ValidateCodeGenerator validateCodeGenerator) {
        this.validateCodeGenerator = validateCodeGenerator;
    }

    @Override
    protected void send(ValidateCode validateCode) throws Exception {
        log.info("手机号{} 发送验证码{}", RequestHolder.getRequest().getParameter("phone"), validateCode.getCode());
    }

    @Override
    protected ValidateCode generate() {
        return validateCodeGenerator.generateCode(RequestHolder.getRequest());
    }
}
