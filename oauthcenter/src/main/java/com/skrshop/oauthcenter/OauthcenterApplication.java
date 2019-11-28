package com.skrshop.oauthcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@MapperScan(basePackages = {"com.skrshop.oauthcenter.repo"})
@SpringBootApplication
@EnableAuthorizationServer
public class OauthcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthcenterApplication.class, args);
    }

}
