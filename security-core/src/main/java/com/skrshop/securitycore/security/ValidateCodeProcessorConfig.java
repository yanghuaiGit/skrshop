package com.skrshop.securitycore.security;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import com.skrshop.securitycore.validate.code.image.ImageCodeGenerator;
import com.skrshop.securitycore.validate.code.image.ImageValidateCodeProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ValidateCodeProcessorConfig {

    @Bean
    public ValidateCodeGenerator imageCodeGenerator(SkrShopSecurityCenterProperties securityProperties) {
        return new ImageCodeGenerator(securityProperties);
    }

    @Bean
    public ValidateCodeProcessor imageValidateCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGeneratorMap, ValidateCodeStore validateCodeStore) {
        return new ImageValidateCodeProcessor(validateCodeGeneratorMap, validateCodeStore);
    }

}
