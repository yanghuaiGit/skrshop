package com.skrshop.oauthcore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = SkrShopSecurityCenterProperties.AUTH_PREFIX)
@Data
public class SkrShopSecurityCenterProperties {
    static final String AUTH_PREFIX = "skrshop.authority";

    //token配置
    private TokenProperties token = new TokenProperties();

}
