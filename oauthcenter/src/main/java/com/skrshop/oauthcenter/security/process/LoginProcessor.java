package com.skrshop.oauthcenter.security.process;


import com.skrshop.common.error.ServiceException;
import com.skrshop.oauthcenter.common.enums.LoginTypeEnum;
import com.skrshop.oauthcenter.model.AuthResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public interface LoginProcessor {
    Logger LOGGER = LoggerFactory.getLogger(LoginProcessor.class);

    /**
     * 获取 登录类型 1普通登录 2快捷登录
     *
     * @return the type
     */
    LoginTypeEnum getLoginTypeEnum();

    /**
     * 获取用户名
     *
     * @param request the request
     * @return the string
     */
    String obtainUsername(ServletRequest request);

    /**
     * 获取密码
     *
     * @param request the request
     * @return the string
     */
    String obtainPassword(ServletRequest request);

    /**
     * 获取来源
     *
     * @param request
     * @return
     */
    default Long obtainResource(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            return Long.valueOf(httpServletRequest.getHeader("resource"));
        } catch (Exception e) {
            LOGGER.error("获取登录来源出现异常", e);
            throw new ServiceException(AuthResultCode.LOGIN_SOURCE_INVALID);
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
