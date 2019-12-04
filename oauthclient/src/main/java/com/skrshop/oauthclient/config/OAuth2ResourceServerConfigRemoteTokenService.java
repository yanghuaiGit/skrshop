package com.skrshop.oauthclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigRemoteTokenService extends ResourceServerConfigurerAdapter {

    @Bean
    public TokenStore tokenStore (){
        return  new InMemoryTokenStore();
    }


//@Bean
//    public TokenStore tokenStore() {
//
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(JwtAccessTokenConverter.class)
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey("test_key");//配置JWT使用的秘钥
//        return accessTokenConverter;
//    }


    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.anonymous()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
//                .and()
//                .requestMatchers()
//                .antMatchers("/api/**")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler())
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }


//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices() {
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        //通过服务注册与发现获取到对应的域名
//        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        tokenService.setClientId("c1");
//        tokenService.setClientSecret("secret");
//        return tokenService;
//    }

}
