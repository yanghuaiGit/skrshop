package com.skrshop.securitycore.validate.code;

import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * 获取对应的验证码生成和验证处理器
 */
@AllArgsConstructor
public class ValidateCodeProcessorHolder {

    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 通过校验类型获取对应的
     *
     * @param type 验证码类型
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return validateCodeProcessors.get(type.getValidType() + "ValidateCodeProcessor");
    }
}
