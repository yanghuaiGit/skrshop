package com.skrshop.oauthcenter.endpoint;


import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.vo.ImageCode;
import com.skrshop.oauthcenter.util.ImageCodeUtil;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidCodeController {

    private final String IMAGECODEKEY = "security_imagecode";

    @Resource
    private ValueOperations<String, String> valueOperations;

    @RequestMapping("/code")
    public void createImageCode(@RequestParam Long phone, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ImageCode imageCode = ImageCodeUtil.createCode();
        try {
            ImageIO.write(imageCode.getBuffImg(), "JPEG", httpServletResponse.getOutputStream());
        } catch (IOException e) {
            throw new SkrShopException(AuthResultCode.IMAGE_CODE_CREATE_ERROR);
        }
        valueOperations.set(IMAGECODEKEY + phone, imageCode.getCode());
    }
}
