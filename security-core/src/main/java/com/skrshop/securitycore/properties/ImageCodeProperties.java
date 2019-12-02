package com.skrshop.securitycore.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends CodeProperties {

    /**
     * 验证码宽度
     */
    private int width = 70;
    /**
     * 验证码图片高度
     */
    private int height = 28;

    public ImageCodeProperties() {
        super(4);
    }



}
