package com.skrshop.oauthcenter.config;

import com.skrshop.oauthcenter.security.config.SkrShopAuthorityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }
        if (LoginTypeEnum.JSON.equals(skrShopAuthorityCenterProperties.getSecurity().getLoginTypeEnum())) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            map.put("flag", "failure_login");
            response.getWriter().write(map.toString());
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
