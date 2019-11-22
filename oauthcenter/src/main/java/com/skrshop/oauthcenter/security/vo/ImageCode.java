package com.skrshop.oauthcenter.security.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode {

    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 5;
    // 验证码干扰线数
    private int lineCount = 150;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    private LocalDateTime expireTime;


}
