package com.skrshop.securitycore.validate.code.image;


import com.skrshop.securitycore.validate.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCode extends ValidateCode {
    //验证码图片
    private BufferedImage image;

    /**
     * 多长时间后过期
     *
     * @param image    流对象
     * @param code     验证码值
     * @param expireIn 过期时间单位为秒
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}
