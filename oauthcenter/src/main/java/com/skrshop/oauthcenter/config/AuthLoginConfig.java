package com.skrshop.oauthcenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AuthLoginConfig.AUTH_PREFIX)
public class AuthLoginConfig {
    static final String AUTH_PREFIX = "authcenter.login";
    //Mybtais类似的provider 登录方式
    //自定义登录路径以及登录方式
}
