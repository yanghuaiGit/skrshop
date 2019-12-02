package com.skrshop.securitycore.validate;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 验证码配置
 */
@Component
public class ValidateCodeSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**
     * @see ValidateCodeFilter  目前融合了短信和图形验证码的验证功能
     */
    @Resource
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) {
        // 由源码得知，在最前面的是UsernamePasswordAuthenticationFilter
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
