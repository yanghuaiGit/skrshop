package com.skrshop.securitycore.security;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import com.skrshop.securitycore.validate.code.image.ImageCode;
import com.skrshop.securitycore.validate.code.image.ImageCodeGenerator;
import com.skrshop.securitycore.validate.code.image.ImageValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.image.ImageValidateCodeStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ValidateCodeProcessorConfig {
    @Resource
    SkrShopSecurityCenterProperties securityProperties;

    @Bean
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGenerator(securityProperties);
    }

    @Bean
    public ValidateCodeStore<ImageCode> imageValidateCodeStore() {
        return new ImageValidateCodeStore(imageCodeGenerator());
    }

    @Bean
    public ValidateCodeProcessor imageValidateCodeProcessor() {
        return new ImageValidateCodeProcessor(imageValidateCodeStore());
    }

}
