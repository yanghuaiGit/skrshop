package com.skrshop.oauthcenter.security.login;


import com.skrshop.oauthcenter.security.config.properties.SkrShopSecurityCenterProperties;
import com.skrshop.oauthcenter.security.process.LoginProcessor;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@EnableConfigurationProperties(SkrShopSecurityCenterProperties.class)
@Data
public class LoginManager {

    private final SkrShopSecurityCenterProperties properties;
    private final List<LoginProcessor> loginProcessors;

    public LoginManager(SkrShopSecurityCenterProperties properties,
                        ObjectProvider<List<LoginProcessor>> loginProcessorsProvider) {
        this.properties = properties;
        this.loginProcessors = loginProcessorsProvider.getIfAvailable();
    }

}
