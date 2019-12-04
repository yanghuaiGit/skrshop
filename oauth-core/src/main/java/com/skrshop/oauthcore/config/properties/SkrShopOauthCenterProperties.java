package com.skrshop.oauthcore.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = SkrShopOauthCenterProperties.AUTH_PREFIX)
@Data
public class SkrShopOauthCenterProperties {
    static final String AUTH_PREFIX = "skrshop.authority";

    //token配置
    private TokenCodeProperties token = new TokenCodeProperties();

}
