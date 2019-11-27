package com.skrshop.oauthcenter.security.validate.code;

public interface ValidateCodeProcessor {
    String IMAGECODEKEY = "security_imagecode_";
    /**
     * 创建校验码
     */
    void create() throws Exception;
}
