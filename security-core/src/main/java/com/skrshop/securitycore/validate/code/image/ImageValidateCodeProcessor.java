package com.skrshop.securitycore.validate.code.image;


import com.skrshop.securitycore.security.SecurityConstants;
import com.skrshop.securitycore.validate.AbsctractValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;


public class ImageValidateCodeProcessor
        extends AbsctractValidateCodeProcessor<ImageCode> {
    /**
     * 设置session的key
     */
    private final String IMAGE_SESSION_VALIDATE_CODE_KEY =
            VALIDATE_CODE_KEY_PREFIX.concat(ValidateCodeType.IMAGE.getValidType());

    /**
     * 实现生成验证码发送到用户浏览器中
     *
     * @param request      request请求
     * @param validateCode 图形验证码
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

    @Override
    public <V> void save(ServletWebRequest request, V validateCode) {

    }

    @Override
    protected String getValidateSeesionKey() {
        return IMAGE_SESSION_VALIDATE_CODE_KEY;
    }

    @Override
    protected String getValidateParameterName() {
        return SecurityConstants.DEFAULT_IMAGE_CODE_PARAMETER_NAME;
    }
}
