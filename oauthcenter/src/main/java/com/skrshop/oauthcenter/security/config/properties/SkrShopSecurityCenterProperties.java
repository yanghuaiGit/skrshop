package com.skrshop.oauthcenter.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = SkrShopSecurityCenterProperties.AUTH_PREFIX)
@Data
public class SkrShopSecurityCenterProperties {
    static final String AUTH_PREFIX = "skrshop.authority";

    //security配置
    private SkrShopSecurityproperties security = new SkrShopSecurityproperties();
    //Mybtais类似的provider 登录方式
    //自定义登录路径以及登录方式
}
