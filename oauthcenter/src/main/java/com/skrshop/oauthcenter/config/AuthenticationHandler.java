package com.skrshop.oauthcenter.config;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
@Slf4j
public class AuthenticationHandler {
    /**
     * 处理登录成功后返回 JWT Token 对.
     *
     * @return the authentication success process
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (response.isCommitted()) {
                log.debug("Response has already been committed");
                return;
            }
            Map<String, Object> map = new HashMap<>(5);
            map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            map.put("flag", "success_login");
            User principal = (User) authentication.getPrincipal();

            String username = principal.getUsername();
            Collection<GrantedAuthority> authorities = principal.getAuthorities();
            Set<String> roles = new HashSet<>();
            if (CollectionUtil.isNotEmpty(authorities)) {
                for (GrantedAuthority authority : authorities) {
                    String roleName = authority.getAuthority();
                    roles.add(roleName);
                }
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("status", "success");
            writer.write(responseMap.toString());

//            JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair(username, roles, null);
//
//            map.put("access_token", jwtTokenPair.getAccessToken());
//            map.put("refresh_token", jwtTokenPair.getRefreshToken());
//
//            ResponseUtil.responseJsonWriter(response, RestBody.okData(map, "登录成功"));
        };
    }

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
