package com.skrshop.oauthcenter.endpoint;


import com.skrshop.common.context.RequestHolder;
import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.validate.code.ImageCode;
import com.skrshop.oauthcenter.util.ImageCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
@Slf4j
public class ValidCodeController {

    private final String IMAGECODEKEY = "security_imagecode";

    @Resource
    private ValueOperations<String, String> valueOperations;

    @GetMapping("/code/image")
    public void createImageCode(@RequestParam Long phone) {
        ImageCode imageCode = ImageCodeUtil.createCode();
        try {
            ImageIO.write(imageCode.getBuffImg(), "JPEG", RequestHolder.getResponse().getOutputStream());
        } catch (IOException e) {
            throw new SkrShopException(AuthResultCode.IMAGE_CODE_CREATE_ERROR);
        }
        log.info("请求的图像验证码 {}" , imageCode.getCode() );
        valueOperations.set(IMAGECODEKEY + phone, imageCode.getCode());
    }

    @GetMapping("/code/sms")
    public void createSms(@RequestParam Long phone) {

    }

}
