package com.skrshop.securitycore.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 李建珍
 * @date 2019/3/24
 */
@AllArgsConstructor
@Getter
public enum ValidateCodeType {
    SMS("sms"),
    IMAGE("image");


    private String validType;


}
