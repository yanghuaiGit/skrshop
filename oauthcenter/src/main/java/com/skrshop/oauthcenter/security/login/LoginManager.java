package com.skrshop.oauthcenter.security.login;


import com.skrshop.oauthcenter.config.AuthLoginConfig;
import com.skrshop.oauthcenter.security.process.LoginProcessor;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@EnableConfigurationProperties(AuthLoginConfig.class)
@Data
public class LoginManager {

    private final AuthLoginConfig properties;
    private final List<LoginProcessor> loginProcessors;

    public LoginManager(AuthLoginConfig properties,
                        ObjectProvider<List<LoginProcessor>> loginProcessorsProvider) {
        this.properties = properties;
        this.loginProcessors = loginProcessorsProvider.getIfAvailable();
    }

}
