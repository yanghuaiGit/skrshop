package com.skrshop.securitycore.validate;

import com.skrshop.securitycore.validate.code.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码前缀
     */
    String VALIDATE_CODE_KEY_PREFIX = "KEY_CODE_FOR_";

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

    /**
     * 获取请求路径的后半段
     */
    default String getProcessorType(ServletWebRequest request) {
        return StringUtils
                .substringAfter(request.getRequest().getRequestURI(),
                        "/validate/");
    }
}
