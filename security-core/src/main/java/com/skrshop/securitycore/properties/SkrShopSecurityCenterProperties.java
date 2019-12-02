package com.skrshop.securitycore.properties;

import com.skrshop.securitycore.security.SecurityConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = SkrShopSecurityCenterProperties.AUTH_PREFIX)
@Data
public class SkrShopSecurityCenterProperties {
    static final String AUTH_PREFIX = "skrshop.authority";

    private String loginpage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private ValidateCodeProperties code = new ValidateCodeProperties();

    //Mybtais类似的provider 登录方式
    //自定义登录路径以及登录方式
}
