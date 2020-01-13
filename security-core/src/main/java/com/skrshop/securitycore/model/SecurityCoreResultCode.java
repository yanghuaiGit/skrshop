package com.skrshop.securitycore.model;

import com.skrshop.common.error.ResultCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SecurityCoreResultCode implements ResultCode {
    private int code;

    private String desc;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getMsg() {
        return msg;
    }


    private SecurityCoreResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    //登录方式异常
    public static ResultCode CODE_TYPE_NOT_SUPPORT = new SecurityCoreResultCode(4000, "Code Type Not Support");


}
