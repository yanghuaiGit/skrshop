package com.skrshop.earthsystem.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.skrshop.common.enums.BaseEnum;

/**
 * 账户类型枚举
 */
public enum AccountTypeEnum implements BaseEnum {
    STAFF(0, "员工"),
    USER(1, "用户");

    @EnumValue//标记数据库存的值是code
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