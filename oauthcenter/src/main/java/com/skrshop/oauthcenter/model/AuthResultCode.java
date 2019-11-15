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
        return null;
    }

    @Override
    public String getMsg() {
        return null;
    }

    //获取登录来源异常
    public static ResultCode LOGIN_SOURCE_INVALID = new AuthResultCode(4004, "Source Invalid");

    //登录来源不存在
    public static ResultCode LOGIN_SOURCE_NOn_EXISTENT = new AuthResultCode(4004, "Source Non Existent");
    //token 无效
    public static ResultCode TOKEN_INVALID = new AuthResultCode(4004, "Token Invalid");

    //token 过期
    public static ResultCode TOKEN_EXPIRE = new AuthResultCode(4005, "Token Expire");
}
