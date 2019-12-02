package com.skrshop.securitycore.validate;

import com.skrshop.securitycore.validate.code.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时候的前缀
     */
    String SESSION_VALIDATE_CODE_KEY_PREFIX = "SESSION_KEY_CODE_FOR_";

    /**
     * 创建校验码
     *
     * @param request 封装请求和响应的对象
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证校验码的有效性
     *
     * @param request 封装请求和响应的对象
     * @throws ValidateCodeException 校验失败
     */
    void validate(ServletWebRequest request) throws ValidateCodeException;
}
