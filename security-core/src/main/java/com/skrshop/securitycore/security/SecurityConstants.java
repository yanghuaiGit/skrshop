package com.skrshop.securitycore.security;

/**
 * 配置常量信息
 */
public interface SecurityConstants {

    /**
     * 验证码操作相关的前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/validate";
    /**
     * 请求需要认证时候默认跳转的请求地址
     */
    String DEFAULT_NEED_AUTHENTICATION_URL = "/security/require";
    /**
     * form页面提交登录请求地址
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FOORM = "/login/form";
    /**
     * 手机验证码登录请求地址
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/login/mobile";
    /**
     * 默认的登录页面
     */
    String DEFAULT_LOGIN_PAGE_URL = "/login.html";

    /**
     * 图片验证码请求默认的参数名称
     */
    String DEFAULT_IMAGE_CODE_PARAMETER_NAME = "imageCode";

    /**
     * 短信验证码时，请求参数中默认携带短信验证码的参数名称
     */
    String DEFAULT_SMS_CODE_PARAMETER_NAME = "smsCode";
    /**
     * 发送短信验证码是传递手机号的参数的名称，请求参数的name
     */
    String DEFAULT_MOBILE_AUTH_LOGIN_PARAMETER_NAME = "mobile";

}
