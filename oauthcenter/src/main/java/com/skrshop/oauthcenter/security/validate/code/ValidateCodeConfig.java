package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import com.skrshop.oauthcenter.security.validate.code.sms.DefaultSmsCodeSender;
import com.skrshop.oauthcenter.security.validate.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfig {


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties) {
        return new ImageCodeGenerator(skrShopAuthorityCenterProperties);
    }


    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator(SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties) {
        return new SmsCodeGenerator(skrShopAuthorityCenterProperties);
    }


    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
