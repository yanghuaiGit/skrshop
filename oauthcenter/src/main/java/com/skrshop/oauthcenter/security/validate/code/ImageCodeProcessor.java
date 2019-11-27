package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.common.context.RequestHolder;
import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;

import javax.imageio.ImageIO;
import java.io.IOException;


public class ImageCodeProcessor extends AbsTractValidateCodeProcessor {


    private ValidateCodeGenerator validateCodeGenerator;


    public ImageCodeProcessor(ValidateCodeGenerator validateCodeGenerator) {
        this.validateCodeGenerator = validateCodeGenerator;
    }

    @Override
    protected void send(ValidateCode validateCode) throws Exception {
        try {
            ImageIO.write(((ImageCode) validateCode).getBuffImg(), "JPEG", RequestHolder.getResponse().getOutputStream());
        } catch (IOException e) {
            throw new SkrShopException(AuthResultCode.IMAGE_CODE_CREATE_ERROR);
        }
    }

    @Override
    protected ValidateCode generate() {
        return validateCodeGenerator.generateCode(RequestHolder.getRequest());
    }
}
