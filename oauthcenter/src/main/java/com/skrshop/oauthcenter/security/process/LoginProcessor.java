package com.skrshop.oauthcenter.security.process;


import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.userdetail.AuthUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public interface LoginProcessor {
    Logger LOGGER = LoggerFactory.getLogger(LoginProcessor.class);

    int logintype();

    AuthUserDetail getUserDetails(String name, String verify);

    /**
     * 获取来源
     * 默认是从resource头中获取到
     */
    default Long obtainResource(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            return Long.valueOf(httpServletRequest.getHeader("resource"));
        } catch (Exception e) {
            LOGGER.error("获取登录来源出现异常", e);
            throw new SkrShopException(AuthResultCode.LOGIN_SOURCE_INVALID);
        }
    }

    /**
     * 此Filter是否匹配
     *
     * @param request
     * @return
     */
    Boolean match(ServletRequest request);


}
