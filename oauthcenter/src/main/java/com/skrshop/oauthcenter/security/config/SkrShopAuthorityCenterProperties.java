package com.skrshop.oauthcenter.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SkrShopAuthorityCenterProperties.AUTH_PREFIX)
@Data
public class SkrShopAuthorityCenterProperties {
    static final String AUTH_PREFIX = "skrshop.authority";

    //跳转的页面
    private SkrShopSecurityproperties security = new SkrShopSecurityproperties();
    //Mybtais类似的provider 登录方式
    //自定义登录路径以及登录方式
}
