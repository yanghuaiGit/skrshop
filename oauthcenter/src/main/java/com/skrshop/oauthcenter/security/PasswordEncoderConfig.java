package com.skrshop.oauthcenter.security;

import com.skrshop.oauthcenter.security.oauth.CustomClientDetailsService;
import com.skrshop.oauthcenter.security.userdetail.UserDetailsRepositoryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public UserDetailsRepositoryManager userDetailsRepository() {
        return new UserDetailsRepositoryManager(passwordEncoder());
    }


    @Bean
    public ClientDetailsService clientDetails() {
        return new CustomClientDetailsService(passwordEncoder());
    }
}
