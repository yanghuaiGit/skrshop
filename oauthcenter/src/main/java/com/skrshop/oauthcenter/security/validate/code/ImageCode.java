package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.common.utils.DateUtil;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode {

    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;
    //过期时间
    private LocalDateTime expireTime;

    public ImageCode() {
    }

    public ImageCode(String code, BufferedImage buffImg, Long expirin) {
        this.code = code;
        this.buffImg = buffImg;
        this.expireTime = DateUtil.DateGet.currentDateTime().plusSeconds(expirin);
    }


    public ImageCode(String code, BufferedImage buffImg, LocalDateTime expireTime) {
        this.code = code;
        this.buffImg = buffImg;
        this.expireTime = expireTime;
    }
}
