package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfig {


    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties) {
        return new ImageCodeGenerator(skrShopAuthorityCenterProperties);
    }
}
