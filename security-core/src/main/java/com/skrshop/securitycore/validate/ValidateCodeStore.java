package com.skrshop.securitycore.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeStore<V extends ValidateCode> extends ValidateCodeGenerator<V> {
    void save(ServletWebRequest request, String key, V validateCode);

    V getCode(ServletWebRequest request, String key);

    boolean remove(ServletWebRequest request, String key);

}
