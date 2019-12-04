package com.skrshop.oauthcenter.oauth.config;

import com.skrshop.oauthcenter.oauth.CustomClientDetailsService;
import com.skrshop.oauthcore.config.AbstractOAuth2AuthorizationServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import javax.annotation.Resource;


/**
 * 授权服务器设置
 */
@EnableAuthorizationServer
@Configuration
@Slf4j
public class OAuth2AuthorizationServerConfig extends AbstractOAuth2AuthorizationServerConfig {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    public ClientDetailsService clientDetails() {
        return new CustomClientDetailsService(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        applyConfigure(endpoints);
    }
}
