package com.skrshop.oauthcenter.security.oauth;

import com.skrshop.oauthcore.config.AbstractOAuth2AuthorizationServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


/**
 * 授权服务器设置
 */
@EnableAuthorizationServer
@Configuration
@Slf4j
public class OAuth2AuthorizationServerConfig extends AbstractOAuth2AuthorizationServerConfig {


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        applyConfigure(endpoints);
    }
}
