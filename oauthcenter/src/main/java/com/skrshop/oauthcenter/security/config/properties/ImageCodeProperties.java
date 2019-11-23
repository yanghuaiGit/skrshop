package com.skrshop.oauthcenter.security.config.properties;

import lombok.Data;

@Data
public class ImageCodeProperties {

    private int width = 180;
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 6;
    // 验证码干扰线数
    private int lineCount = 10;

    //需要验证码拦截的url以,隔开
    private String url;
}
