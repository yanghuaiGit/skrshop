package com.skrshop.oauthcenter.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    /**
     * 普通登录
     */
    NORMAL_LOGIN(1, "NORMAL_LOGIN"),
    /**
     * 快捷登录
     */
    QUICK_LOGIN(2, "QUICK_LOGIN");

    private int type;
    private String desc;
}
