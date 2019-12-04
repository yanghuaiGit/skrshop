package com.skrshop.oauthcenter.endpoint;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.WebSecurityConfig;
import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class SecurityController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private SkrShopSecurityCenterProperties skrShopSecurityCenterProperties;


    /**
     * 当需要身份认证时跳转到此地址
     */
    @RequestMapping(WebSecurityConfig.LOGIN_PAGE)
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            log.info("==========/authentication/require===============");
            redirectStrategy.sendRedirect(request, response, skrShopSecurityCenterProperties.getLoginpage());
        } catch (IOException e) {
            throw new SkrShopException(AuthResultCode.REDIRECT_ERROR);
        }

    }
}
