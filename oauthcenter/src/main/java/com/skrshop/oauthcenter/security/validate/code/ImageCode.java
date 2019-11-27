package com.skrshop.oauthcenter.security.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode  extends ValidateCode {


    // 验证码图片Buffer
    private BufferedImage buffImg = null;


    public ImageCode() {
    }

    public ImageCode(String code, BufferedImage buffImg, Long expirin) {
        super(code,expirin);
        this.buffImg = buffImg;
    }


    public ImageCode(String code, BufferedImage buffImg, LocalDateTime expireTime) {
        super(code,expireTime);
        this.buffImg = buffImg;

    }
}
