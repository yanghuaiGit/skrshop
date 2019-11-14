package com.skrshop.oauthcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;


/**
 * 授权服务器配置
 */
//@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Bean
    public ClientDetailsService clientDetailsService() {
        return new InMemoryClientDetailsService();
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService());
        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setTokenStore(tokens);
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);
        return defaultTokenServices;
    }

    /**
     * 配置客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("c1")
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("resources1")
                .authorizedGrantTypes()
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");

    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}
