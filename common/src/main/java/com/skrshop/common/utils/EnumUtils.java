package com.skrshop.common.utils;

import com.skrshop.common.enums.BaseEnum;

import java.util.Arrays;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class EnumUtils {
    public static <T extends Enum<?> & BaseEnum> T codeOf(Class<T> enumClass, int code) {
        T[] enumConstants = enumClass.getEnumConstants();
        return Arrays.stream(enumConstants).filter(e -> e.getCode() == code).findFirst().orElse(null);
    }
}
