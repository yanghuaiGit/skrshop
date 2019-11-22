package com.skrshop.oauthcenter.model;

import com.skrshop.common.error.ResultCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthResultCode implements ResultCode {
    private int code;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


    //登录方式异常
    public static ResultCode LOGIN_TYPE_NOT_SUPPORT = new AuthResultCode(4001, "Login Type Not Support");

    //获取登录来源异常
    public static ResultCode LOGIN_SOURCE_INVALID = new AuthResultCode(4002, "Source Invalid");

    //登录来源不存在
    public static ResultCode LOGIN_SOURCE_NOn_EXISTENT = new AuthResultCode(4003, "Source Non Existent");

    //token 无效
    public static ResultCode TOKEN_INVALID = new AuthResultCode(4004, "Token Invalid");

    //跳转失败
    public static ResultCode REDIRECT_ERROR = new AuthResultCode(4005, "Redirect Error");

    //无权限 请登录
    public static ResultCode NO_AUTHORITY = new AuthResultCode(4010, "No Aythority");

    //token 过期
    public static ResultCode TOKEN_EXPIRE = new AuthResultCode(4011, "Token Expire");

    //图片验证码生成失败
    public static ResultCode IMAGE_CODE_CREATE_ERROR = new AuthResultCode(4020, "ImageCode Create Error");


}
