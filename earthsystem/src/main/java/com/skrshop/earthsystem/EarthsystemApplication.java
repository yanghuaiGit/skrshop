package com.skrshop.earthsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.skrshop.earthsystem.repo"})
public class EarthsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EarthsystemApplication.class, args);
    }

}
