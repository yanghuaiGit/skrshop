package com.skrshop.oauthcenter.endpoint;


import com.skrshop.common.context.RequestHolder;
import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.validate.code.ImageCode;
import com.skrshop.oauthcenter.security.validate.code.ValidateCode;
import com.skrshop.oauthcenter.security.validate.code.ValidateCodeGenerator;
import com.skrshop.oauthcenter.security.validate.code.sms.SmsCodeSender;
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


    @Resource
    private ValueOperations<String, String> valueOperations;

    @Resource
    private ValidateCodeGenerator imageCodeGenerator;

    @Resource
    private ValidateCodeGenerator smsCodeGenerator;

    @Resource
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createImageCode() {
        String IMAGECODEKEY = "security_imagecode";
        //图形验证码暂时不需要request
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generateCode(null);
        try {
            ImageIO.write(imageCode.getBuffImg(), "JPEG", RequestHolder.getResponse().getOutputStream());
        } catch (IOException e) {
            throw new SkrShopException(AuthResultCode.IMAGE_CODE_CREATE_ERROR);
        }
        log.info("请求的图像验证码 {}", imageCode.getCode());
        valueOperations.set(IMAGECODEKEY, imageCode.getCode());
    }

    @GetMapping("/code/sms")
    public void createSms(@RequestParam long phone) {
        ValidateCode smsCode = smsCodeGenerator.generateCode(null);
        smsCodeSender.sendCode(phone, smsCode.getCode());
    }

}
