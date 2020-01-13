package com.skrshop.securitycore.validate.code.sms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Optional;


public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired(required = false)
    private AuthenticationSuccessHandler successHandler;

    @Autowired(required = false)
    private AuthenticationFailureHandler failHandler;

    @Resource
    private UserDetailsService userDetailsRepository;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        Optional.ofNullable(successHandler)
                .ifPresent(item -> smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler));

        Optional.ofNullable(failHandler)
                .ifPresent(item -> smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failHandler));

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsRepository);

        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
