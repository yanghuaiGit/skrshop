package com.skrshop.securitycore.validate.code.image;


import com.skrshop.securitycore.security.SecurityConstants;
import com.skrshop.securitycore.validate.AbsctractValidateCodeProcessor;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import com.skrshop.securitycore.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.util.Map;
import java.util.Objects;


public class ImageValidateCodeProcessor
        extends AbsctractValidateCodeProcessor<ImageCode> {


    /**
     * 设置session的key
     */
    private final String IMAGE_SESSION_VALIDATE_CODE_KEY =
            VALIDATE_CODE_KEY_PREFIX.concat(ValidateCodeType.IMAGE.getValidType());


    public ImageValidateCodeProcessor(Map<String, ValidateCodeGenerator> validateCodeGenerators, ValidateCodeStore validateCodeStore) {
        super(validateCodeGenerators,validateCodeStore);

    }

    /**
     * 实现生成验证码发送到用户浏览器中
     *
     * @param request      request请求
     * @param validateCode 图形验证码
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        if (Objects.nonNull(request.getResponse())) {
            ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
        }
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
