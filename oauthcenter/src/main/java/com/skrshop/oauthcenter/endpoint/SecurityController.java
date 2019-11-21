package com.skrshop.oauthcenter.endpoint;

import com.skrshop.common.error.ServiceException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.config.SkrShopAuthorityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class SecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    /**
     * 当需要身份认证时跳转到此地址
     */
    @RequestMapping("/authentication/require")
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("跳转到url {}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                try {
                    redirectStrategy.sendRedirect(request, response, skrShopAuthorityCenterProperties.getSecurity().getLoginpage());
                } catch (IOException e) {
                    log.error("跳转失败{}", redirectUrl);
                    throw new ServiceException(AuthResultCode.REDIRECT_ERROR);
                }
            }
        }
        throw new ServiceException(AuthResultCode.NO_AUTHORITY);
    }
}
