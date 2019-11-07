package com.skrshop.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.skrshop.mall.repo"})
@EnableFeignClients(basePackages = {"com.skrshop.earthsystemapi.api"})
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
