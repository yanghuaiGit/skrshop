package com.skrshop.securitycore.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 *
 * @author 李建珍
 */
public interface ValidateCodeGenerator {

    /**
     * 校验码生成器
     *
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
