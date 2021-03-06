package com.skrshop.oauthcenter.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skrshop.oauthcenter.security.handler.AuthenticationFailureHandler;
import com.skrshop.oauthcenter.security.handler.AuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.annotation.Resource;

@Configuration
public class HandlerConfig {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Resource
    private PasswordEncoder passwordEncoder;
    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandler")
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandler")
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler(RequestCache requestCache) {
        return new AuthenticationSuccessHandler(objectMapper,requestCache,clientDetailsService,authorizationServerTokenServices,passwordEncoder);
    }


}
