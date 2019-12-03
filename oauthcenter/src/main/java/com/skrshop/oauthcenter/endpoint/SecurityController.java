package com.skrshop.oauthcenter.endpoint;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class SecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private SkrShopSecurityCenterProperties skrShopSecurityCenterProperties;


    /**
     * 当需要身份认证时跳转到此地址
     */
    @RequestMapping("/authentication/require")
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            if (StringUtils.endsWithIgnoreCase(savedRequest.getRedirectUrl(), ".html")) {
                try {
                    redirectStrategy.sendRedirect(request, response, skrShopSecurityCenterProperties.getLoginpage());
                } catch (IOException e) {
                    log.error("跳转失败{}", savedRequest.getRedirectUrl());
                    throw new SkrShopException(AuthResultCode.REDIRECT_ERROR);
                }
            }
        }
        throw new SkrShopException(AuthResultCode.NO_AUTHORITY);
    }
}
