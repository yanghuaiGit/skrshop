package com.skrshop.securitycore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Optional;

/**
 * security-core表单登录配置
 */
public class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired(required = false)
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 密码登录配置相关
     *
     * @param httpSecurity
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception {
        FormLoginConfigurer<HttpSecurity> formConfigurer = httpSecurity.formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FOORM);

        Optional.ofNullable(authenticationSuccessHandler)
                .ifPresent(item -> formConfigurer.successHandler(authenticationSuccessHandler));

        Optional.ofNullable(authenticationFailureHandler)
                .ifPresent(item -> formConfigurer.failureHandler(authenticationFailureHandler));


    }
}
