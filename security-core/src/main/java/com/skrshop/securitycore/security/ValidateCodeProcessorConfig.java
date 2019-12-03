package com.skrshop.securitycore.security;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCode;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import com.skrshop.securitycore.validate.code.image.ImageCode;
import com.skrshop.securitycore.validate.code.image.ImageCodeGenerator;
import com.skrshop.securitycore.validate.code.image.ImageValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.image.ImageValidateCodeStore;
import com.skrshop.securitycore.validate.code.sms.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
public class ValidateCodeProcessorConfig {
    @Resource
    SkrShopSecurityCenterProperties securityProperties;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * imageCode生成器
     */
    @Bean
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGenerator(securityProperties);
    }

    /**
     * smsCode生成器
     */
    @Bean
    public ValidateCodeGenerator smsCodeGenerator() {
        return new SmsCodeGenerator(securityProperties);
    }


    /**
     * smsCode发送器
     */
    @Bean
    public SmsCodeSender smsCodeSender() {
        return new SmsDefaultCodeSender();
    }

    /**
     * imageCode仓库
     */
    @Bean
    public ValidateCodeStore<ImageCode> imageValidateCodeStore() {
        return new ImageValidateCodeStore(imageCodeGenerator());
    }

    /**
     * smsCode仓库
     */
    @Bean
    public ValidateCodeStore<ValidateCode> smsValidateCodeStore() {
        return new SmsValidateCodeStore(redisTemplate, smsCodeGenerator());
    }


    /**
     * imageCode处理
     */
    @Bean
    public ValidateCodeProcessor imageValidateCodeProcessor() {
        return new ImageValidateCodeProcessor(imageValidateCodeStore());
    }

    /**
     * smsCode处理
     */
    @Bean
    public ValidateCodeProcessor smsValidateCodeProcessor() {
        return new SmsValidateCodeProcessor(smsValidateCodeStore(), smsCodeSender());
    }
}
