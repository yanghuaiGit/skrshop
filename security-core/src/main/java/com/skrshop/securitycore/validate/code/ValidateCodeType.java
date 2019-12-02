package com.skrshop.securitycore.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<String> findType(String type) {
        return Arrays.stream(ValidateCodeType.values())
                .map(ValidateCodeType::getValidType)
                .filter(item -> item.equals(type))
                .findFirst()
                ;
    }

}
