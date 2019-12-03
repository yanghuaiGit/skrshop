package com.skrshop.securitycore.security;

import com.skrshop.securitycore.validate.ValidateCodeFilter;
import com.skrshop.securitycore.validate.code.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * security-core表单登录配置
 */
public class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource
    private ValidateCodeFilter validateCodeFilter;

    /**
     * 密码登录配置相关
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //比登录Filter先执行 自定义登录
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FOORM);

        //开启短信验证码登录
        httpSecurity.apply(smsCodeAuthenticationSecurityConfig);


    }
}
