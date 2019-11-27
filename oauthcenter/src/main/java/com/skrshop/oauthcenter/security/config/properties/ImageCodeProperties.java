package com.skrshop.oauthcenter.security.config.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends CodeProperties {

    private int width = 180;
    private int height = 40;
    // 验证码干扰线数
    private int lineCount = 10;

    public ImageCodeProperties() {
        setCodeCount(4);
    }
}
