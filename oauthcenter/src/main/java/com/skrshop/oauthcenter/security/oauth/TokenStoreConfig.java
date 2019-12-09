package com.skrshop.oauthcenter.security.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class TokenStoreConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "skrshop.authority.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "skrshop.authority.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {

        @Bean
        @ConditionalOnMissingBean(TokenStore.class)
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        @ConditionalOnMissingBean(JwtAccessTokenConverter.class)
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            CustomJwtAccessTokenConverter accessTokenConverter = new CustomJwtAccessTokenConverter();
            accessTokenConverter.setSigningKey("test_key");//配置JWT使用的秘钥
            //todo 还需要设置一个校验的配置 进行签名
            //accessTokenConverter.setJwtClaimsSetVerifier();
            return accessTokenConverter;
        }

        /**
         * 扩展jwt内容
         */
        @Bean
        @ConditionalOnMissingBean(name = "skrShopJwtTokenEnhancer")
        public TokenEnhancer skrShopJwtTokenEnhancer() {
            return (accessToken, authentication) -> {
                log.info("=============enhance 扩展JWT===============");
                Map<String, Object> info = new HashMap<>(2);
                info.put("organization", authentication.getName() + "");
                info.put("company", "skrshop");
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
                return accessToken;
            };
        }
    }
}
