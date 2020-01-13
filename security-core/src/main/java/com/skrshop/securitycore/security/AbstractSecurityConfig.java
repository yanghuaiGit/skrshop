package com.skrshop.securitycore.security;

import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.validate.ValidateCodeFilter;
import com.skrshop.securitycore.validate.code.ValidateCodeProcessorHolder;
import com.skrshop.securitycore.validate.code.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * security-core表单登录配置
 */
public class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failHandler;

    @Resource
    private SkrShopSecurityCenterProperties skrShopSecurityCenterProperties;

    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 密码登录配置相关
     */
    protected FormLoginConfigurer<HttpSecurity> applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter(failHandler, skrShopSecurityCenterProperties, validateCodeProcessorHolder);
        validateCodeFilter.afterPropertiesSet();
        FormLoginConfigurer<HttpSecurity> formLoginConfigurer = httpSecurity
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //比登录Filter先执行 自定义登录
                .formLogin()
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FOORM);
        if (Objects.nonNull(successHandler)) {
            formLoginConfigurer.successHandler(successHandler);
        }

        if (Objects.nonNull(failHandler)) {
            formLoginConfigurer.failureHandler(failHandler);
        }
        //开启短信验证码登录
        httpSecurity.apply(smsCodeAuthenticationSecurityConfig);

        return formLoginConfigurer;
    }
}
