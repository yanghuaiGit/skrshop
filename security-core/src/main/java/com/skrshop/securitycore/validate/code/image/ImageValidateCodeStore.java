package com.skrshop.securitycore.validate.code.image;

import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import lombok.AllArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;


@AllArgsConstructor
public class ImageValidateCodeStore implements ValidateCodeStore<ImageCode> {

    private ValidateCodeGenerator imageCodeGenerator;

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

    @Override
    public boolean remove(ServletWebRequest request, String key) {
        request.getRequest().getSession().removeAttribute(key);
        return true;
    }

    @Override
    public ImageCode generate(ServletWebRequest request) {
        return (ImageCode) imageCodeGenerator.generate(request);
    }
}
