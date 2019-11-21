package com.skrshop.oauthcenter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class AuthenticationHandler {


    /**
     * 失败登录处理器 处理登录失败后的逻辑 登录失败返回信息 以此为依据跳转
     *
     * @return the authentication failure process
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            if (response.isCommitted()) {
                log.debug("Response has already been committed");
                return;
            }
            Map<String, Object> map = new HashMap<>(2);

            map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            map.put("flag", "failure_login");
//            ResponseUtil.responseJsonWriter(response, RestBody.build(HttpStatus.UNAUTHORIZED.value(), map, "认证失败","-9999"));
        };
    }
}
