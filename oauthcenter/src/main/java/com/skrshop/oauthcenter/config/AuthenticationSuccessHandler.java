package com.skrshop.oauthcenter.config;

import cn.hutool.core.collection.CollectionUtil;
import com.skrshop.oauthcenter.security.config.SkrShopAuthorityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (LoginTypeEnum.JSON.equals(skrShopAuthorityCenterProperties.getSecurity().getLoginTypeEnum())) {
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
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
