package com.skrshop.securitycore.security;

import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.ValidateCodeProcessorHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
public class ValidateCodeFilterConfig {

//    @Resource
//    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @Bean
    public ValidateCodeProcessorHolder validateCodeProcessorHolder() {
        return new ValidateCodeProcessorHolder(validateCodeProcessors);
    }

    //应该放入
//    @Bean
//    @ConditionalOnBean(ValidateCodeFilter.class)
//    public ValidateCodeFilter validateCodeFilter(SkrShopSecurityCenterProperties skrShopSecurityCenterProperties) {
//        return new ValidateCodeFilter(authenticationFailureHandler, skrShopSecurityCenterProperties, validateCodeProcessorHolder());
//    }
}
