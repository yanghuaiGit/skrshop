package com.skrshop.securitycore.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

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
