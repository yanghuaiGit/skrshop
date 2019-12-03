package com.skrshop.oauthcenter.security.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
//@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SkrShopSecurityCenterProperties skrShopSecurityCenterProperties;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        log.info("--登录成功--");
        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }

        Map<String, Object> map = new HashMap<>(5);
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("flag", "success_login");
        User principal = (User) authentication.getPrincipal();

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
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));
//            JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair(username, roles, null);
//
//            map.put("access_token", jwtTokenPair.getAccessToken());
//            map.put("refresh_token", jwtTokenPair.getRefreshToken());
//
//            ResponseUtil.responseJsonWriter(response, RestBody.okData(map, "登录成功"));
    }
}
