package com.skrshop.securitycore.security;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCodeFilter;
import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.ValidateCodeProcessorHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
public class ValidateCodeFilterConfig {

    @Resource(name = "validateCodeFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @Bean
    public ValidateCodeProcessorHolder validateCodeProcessorHolder() {
        return new ValidateCodeProcessorHolder(validateCodeProcessors);
    }

    @Bean
    public ValidateCodeFilter validateCodeFilter(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
        return new ValidateCodeFilter(authenticationFailureHandler, skrShopSecurityCenterProperties, validateCodeProcessorHolder());
    }
}
