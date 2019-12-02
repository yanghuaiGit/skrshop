package com.skrshop.securitycore.validate.code.image;

import com.skrshop.securitycore.validate.ValidateCodeStore;
import org.springframework.web.context.request.ServletWebRequest;


public class ImageValidateCodeStore implements ValidateCodeStore<ImageCode> {

    @Override
    public void save(ServletWebRequest request, String key, ImageCode validateCode) {
        request.getRequest().getSession()
                .setAttribute(key,
                        validateCode);
    }

    @Override
    public ImageCode getCode(ServletWebRequest request, String key) {
        return (ImageCode) request.getRequest().getSession().getAttribute(key);
    }
}
