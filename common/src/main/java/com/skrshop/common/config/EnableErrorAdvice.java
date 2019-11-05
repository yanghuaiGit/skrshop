package com.skrshop.common.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/10/16
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ErrorAdviceImportSelector.class)
public @interface EnableErrorAdvice {

    String value() default "";
}
