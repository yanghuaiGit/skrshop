package com.skrshop.skrshoptkmybatis.Performance;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

import static com.skrshop.skrshoptkmybatis.Performance.SqlCostInterceptor.DEFAULT_TIME_LIMIT;
import static com.skrshop.skrshoptkmybatis.Performance.SqlCostInterceptor.TIME_LIMIT_KEY;

@Configuration
public class PerformanceConfig {
    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启
    public Interceptor performanceInterceptor() {

        Interceptor sqlCostInterceptor = new SqlCostInterceptor();
        Properties properties = new Properties();
        properties.setProperty(TIME_LIMIT_KEY, DEFAULT_TIME_LIMIT);
        sqlCostInterceptor.setProperties(properties);
        return sqlCostInterceptor;
    }
}
