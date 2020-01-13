package com.skrshop.earthsystemapi.enums;

import com.skrshop.common.enums.BaseEnum;

/**
 * 账户类型枚举
 */
public enum AccountTypeEnum implements BaseEnum {
    STAFF(0, "员工"),
    USER(1, "用户");

    private int code;
    private String desc;

    AccountTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
