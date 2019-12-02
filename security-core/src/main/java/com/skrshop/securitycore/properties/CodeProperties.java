package com.skrshop.securitycore.properties;

import lombok.Data;

@Data
public class CodeProperties {

    //验证码过期时间 s 默认是5分钟
    private int expireIn = 300;

    // 验证码字符个数
    private int codeCount = 6;

    //是否启用
    private Boolean enable = false;

    //需要验证码拦截的url以,隔开
    private String url;
}
