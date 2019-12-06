package com.skrshop.oauthcenter.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 自定义认证成功处理器
 * 使用spring默认的处理器
 */
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final Map<String, String> EMPTY_MAP = new HashMap<>();

    private RequestCache requestCache;
    private ObjectMapper objectMapper;
    private ClientDetailsService clientDetailsService;
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    private PasswordEncoder passwordEncoder;
    @Resource
    private List<TokenEnhancer> tokenEnhancers = Collections.emptyList();


    public AuthenticationSuccessHandler(ObjectMapper objectMapper, RequestCache requestCache
            , ClientDetailsService clientDetailsService, AuthorizationServerTokenServices authorizationServerTokenServices
            , PasswordEncoder passwordEncoder) {
        this.objectMapper = objectMapper;
        this.requestCache = requestCache;
        this.clientDetailsService = clientDetailsService;
        this.authorizationServerTokenServices = authorizationServerTokenServices;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        log.info("登陆成功是否转发请求{}", Objects.nonNull(savedRequest));
        //todo 判断是否是/oauth/*请求
        if (Objects.nonNull(savedRequest)) {
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        } else {
            String header = request.getHeader("Authorization");

            if (header == null || !header.toLowerCase().startsWith("basic ")) {
                throw new UnapprovedClientAuthenticationException("请求中无Client信息");
            }

            String[] tokens = extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (Objects.isNull(clientDetails)) {
                log.info("clientId不存在：{}", clientId);
                throw new UnapprovedClientAuthenticationException("CientId不存在");
            }
            if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                log.info("clientId：{} 对应clientSecret: {}不匹配", clientId, clientSecret);
                throw new UnapprovedClientAuthenticationException("CientSecret不匹配");
            }
            TokenRequest tokenRequest = new TokenRequest(EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

            OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            accessToken = enhance(accessToken, oAuth2Authentication);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(accessToken));


        }

    }

    private String[] extractAndDecodeHeader(String header)
            throws IOException {

        String credentialsCharset = "UTF-8";
        byte[] base64Token = header.substring(6).getBytes(credentialsCharset);
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, credentialsCharset);

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        OAuth2AccessToken result = accessToken;
        for (TokenEnhancer enhancer : tokenEnhancers) {
            result = enhancer.enhance(result, authentication);
        }
        return result;
    }
}
