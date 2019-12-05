package com.skrshop.oauthcenter.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * 自定义认证成功处理器
 * 使用spring默认的处理器
 */
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache;
    private DefaultTokenServices tokenServices;
    private ObjectMapper objectMapper;


    public AuthenticationSuccessHandler(ObjectMapper objectMapper, RequestCache requestCache, DefaultTokenServices tokenServices) {
        this.objectMapper = objectMapper;
        this.requestCache = requestCache;
        this.tokenServices = tokenServices;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        log.info("登陆成功是否转发请求{}", Objects.nonNull(savedRequest));
        if (Objects.nonNull(savedRequest)) {
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        } else {
            response.setContentType("application/json;charset=utf-8");
            if (Objects.nonNull(tokenServices)) {
                //TODO 根据请求获取到对应的数据 自己拼接一个token给前端
                DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
//                accessTokenEnhancer.enhance(token, authentication);
                response.getWriter().write(objectMapper.writeValueAsString(token));
            } else {
                response.getWriter().write(objectMapper.writeValueAsString(authentication));
            }
        }
    }
}
