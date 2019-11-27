package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import com.skrshop.oauthcenter.security.validate.code.sms.DefaultCodeSender;
import com.skrshop.oauthcenter.security.validate.code.sms.CodeSender;
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
    @ConditionalOnMissingBean(name = "imageCodeProcessor")
    public ValidateCodeProcessor imageCodeProcessor(SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties) {
        return new ImageCodeProcessor(imageCodeGenerator(skrShopAuthorityCenterProperties));
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeProcessor")
    public ValidateCodeProcessor smsCodeProcessor(SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties) {
        return new SmsCodeProcessor(smsCodeGenerator(skrShopAuthorityCenterProperties));
    }

    @Bean
    @ConditionalOnMissingBean(CodeSender.class)
    public CodeSender smsCodeSender() {
        return new DefaultCodeSender();
    }
}
