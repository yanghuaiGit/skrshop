package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.oauthcenter.security.config.properties.SkrShopSecurityCenterProperties;
import com.skrshop.oauthcenter.security.validate.code.sms.DefaultCodeSender;
import com.skrshop.oauthcenter.security.validate.code.sms.CodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfig {


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
        return new ImageCodeGenerator(skrShopSecurityCenterProperties);
    }


    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
        return new SmsCodeGenerator(skrShopSecurityCenterProperties);
    }


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeProcessor")
    public ValidateCodeProcessor imageCodeProcessor(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
        return new ImageCodeProcessor(imageCodeGenerator(skrShopSecurityCenterProperties));
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeProcessor")
    public ValidateCodeProcessor smsCodeProcessor(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
        return new SmsCodeProcessor(smsCodeGenerator(skrShopSecurityCenterProperties));
    }

    @Bean
    @ConditionalOnMissingBean(CodeSender.class)
    public CodeSender smsCodeSender() {
        return new DefaultCodeSender();
    }
}
