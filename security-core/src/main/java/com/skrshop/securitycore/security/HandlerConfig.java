package com.skrshop.securitycore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.annotation.Resource;

@Configuration
public class HandlerConfig {
    @Resource
    private ObjectMapper objectMapper;

    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandler")
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "validateCodeFailureHandler")
    public SimpleUrlAuthenticationFailureHandler validateCodeFailureHandler() {
        return new ValidateCodeFailureHandler(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandler")
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler(objectMapper);
    }

}
