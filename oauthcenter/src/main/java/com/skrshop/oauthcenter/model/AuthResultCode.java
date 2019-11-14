package com.skrshop.oauthcenter.model;

import com.skrshop.common.response.ResultCode;
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

    //token 无效
    public static ResultCode TOKEN_Invalid = new AuthResultCode(4004, "Token Invalid");

    //token 过期
    public static ResultCode TOKEN_EXPIRE = new AuthResultCode(4005, "Token Expire");
}
