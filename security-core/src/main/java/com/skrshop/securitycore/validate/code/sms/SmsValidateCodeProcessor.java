package com.skrshop.securitycore.validate.code.sms;


import com.skrshop.common.context.RequestHolder;
import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.rpc.ensure.Ensure;
import com.skrshop.securitycore.security.SecurityConstants;
import com.skrshop.securitycore.validate.AbsctractValidateCodeProcessor;
import com.skrshop.securitycore.validate.ValidateCode;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import com.skrshop.securitycore.validate.code.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


@Slf4j
public class SmsValidateCodeProcessor
        extends AbsctractValidateCodeProcessor<ValidateCode> {

    private SmsCodeSender smsCodeSender;

    /**
     * 设置session的key
     */
    private final String SMS_SESSION_VALIDATE_CODE_KEY =
            VALIDATE_CODE_KEY_PREFIX.concat(ValidateCodeType.SMS.getValidType()).concat("_");


    public SmsValidateCodeProcessor(ValidateCodeStore<ValidateCode> validateCodeStore, SmsCodeSender smsCodeSender) {
        super(validateCodeStore);
        this.smsCodeSender = smsCodeSender;

    }

    /**
     * 实现生成验证码发送到用户浏览器中
     *
     * @param request      request请求
     * @param validateCode 手机验证码
     */
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils
                .getRequiredStringParameter(request.getRequest(),
                        SecurityConstants.DEFAULT_MOBILE_AUTH_LOGIN_PARAMETER_NAME);
        Ensure.that(mobile).isNotBlank(CommonResultCode.PARAM_VALID_ERROR);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

    @Override
    protected String getValidateSeesionKey() {
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(RequestHolder.getRequest(), SecurityConstants.DEFAULT_MOBILE_AUTH_LOGIN_PARAMETER_NAME);
        } catch (ServletRequestBindingException e) {
            log.error("验证码参数解析有错，核对图片验证码参数是否绑定错误，绑定name为{}", getValidateParameterName());
        }
        return SMS_SESSION_VALIDATE_CODE_KEY + codeInRequest;
    }

    @Override
    protected String getValidateParameterName() {
        return SecurityConstants.DEFAULT_SMS_CODE_PARAMETER_NAME;
    }
}
