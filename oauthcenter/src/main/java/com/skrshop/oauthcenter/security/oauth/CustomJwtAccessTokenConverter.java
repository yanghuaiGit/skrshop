package com.skrshop.oauthcenter.security.oauth;

import com.skrshop.oauthcenter.model.JwtInfo;
import com.skrshop.oauthcenter.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

@Slf4j
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();


    @Override
    protected Map<String, Object> decode(String token) {

        try {
            log.info("开始decode{}", token);
            JwtInfo jwtInfo = JwtUtils.parseJwtRS256(token);
            Claims claims = jwtInfo.getClaims();
            claims.put(EXP, jwtInfo.getExp());
            log.info("decode值{}", claims.toString());
            return claims;
        } catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        log.info("开始decode");
//        Map<String, Object> stringMap = new HashMap<>(4);
        Map<String, Object> stringMap = (Map<String, Object>) tokenConverter.convertAccessToken(accessToken, authentication);
        stringMap.put("123", "12");
        String content;
        try {
            content = JwtUtils.buildJwtRS256(stringMap, -1);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot convert access token to JSON", e);
        }
        log.info("encode值{}", content);
        return content;
    }
}
