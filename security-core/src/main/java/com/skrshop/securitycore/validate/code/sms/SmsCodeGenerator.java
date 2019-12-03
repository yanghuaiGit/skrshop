package com.skrshop.securitycore.validate.code.sms;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCode;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;


@AllArgsConstructor
@Slf4j
public class SmsCodeGenerator implements ValidateCodeGenerator {
    private SkrShopSecurityCenterProperties securityProperties;

    /**
     * 生成简单的验证码
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        log.info("手机验证码：{}",code);
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }


}
