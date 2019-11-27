package com.skrshop.oauthcenter.security.config.properties;

import lombok.Data;

@Data
public class CodeProperties {

    //过期时间 s
    private int expireIn = 3600;

    // 验证码字符个数
    private int codeCount = 6;

    //是否启用
    private Boolean enable = false;

    //需要验证码拦截的url以,隔开
    private String url;
}
