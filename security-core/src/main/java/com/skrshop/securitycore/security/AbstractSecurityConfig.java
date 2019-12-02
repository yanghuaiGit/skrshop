package com.skrshop.securitycore.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security-core配置
 */
public class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 密码登录配置相关
     *
     * @param httpSecurity
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FOORM);
    }
}
