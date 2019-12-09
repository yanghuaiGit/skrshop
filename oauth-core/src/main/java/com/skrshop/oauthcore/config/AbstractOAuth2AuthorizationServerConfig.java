package com.skrshop.oauthcore.config;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 授权服务器设置
 */
@Slf4j
public class AbstractOAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Bean
    @Primary
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetails);
        defaultTokenServices.setAccessTokenValiditySeconds(7200);//2小时
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);//2天
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Resource
    private ClientDetailsService clientDetails;


    @Resource
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private List<TokenEnhancer> tokenEnhancer ;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails);
    }

    /**
     * 配置授权端点
     *
     * @param security 授权服务Security配置
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                //配置/oauth/check_token
//                .checkTokenAccess("hasRole('all_Role')")
//                .checkTokenAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    protected void applyConfigure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
                .tokenEnhancer(getTokenEnhancerChain())
                .tokenStore(tokenStore);
        if (Objects.nonNull(jwtAccessTokenConverter)) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    private TokenEnhancerChain getTokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>(6);

        if (Objects.nonNull(jwtAccessTokenConverter)) {
            enhancers.add(jwtAccessTokenConverter);
        }

        if (CollectionUtil.isNotEmpty(tokenEnhancer)) {
            enhancers.addAll(tokenEnhancer);
        }
        tokenEnhancerChain.setTokenEnhancers(enhancers);
        return tokenEnhancerChain;
    }
}
