package com.skrshop.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountStatus {
    DISABLE(0, "disable"),
    ENABLE(1, "enable"),
    DELETE(2, "delete");

    private int code;
    private String desc;


}
