package com.skrshop.securitycore.validate;

import com.skrshop.securitycore.validate.code.ValidateCodeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 对于process的抽象实现
 */
@Slf4j
@AllArgsConstructor
public abstract class AbsctractValidateCodeProcessor<V extends ValidateCode> implements ValidateCodeProcessor {

    protected ValidateCodeStore<V> validateCodeStore;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        //生成验证码
        V validateCode = validateCodeStore.generate(request);
        //保存session和别的地方
        save(request, validateCode);
        //发送到浏览器和短信
        send(request, validateCode);
    }

    /**
     * 发送验证码，手机验证码
     */
    protected abstract void send(ServletWebRequest request, V validateCode) throws Exception;


    private void save(ServletWebRequest request, V validateCode) {
        validateCodeStore.save(request, getValidateSeesionKey(), validateCode);
    }


    /**
     * 做验证码校验的封装
     */
    @Override
    public void validate(ServletWebRequest request) throws ValidateCodeException {
        //从请求中取出之前存入session的验证码
        ValidateCode imageCode = validateCodeStore.getCode(request, getValidateSeesionKey());
        //获取form表单中用户输入的验证码
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), getValidateParameterName());
        } catch (ServletRequestBindingException e) {
            log.error("验证码参数解析有错，核对图片验证码参数是否绑定错误，绑定name为{}", getValidateParameterName());
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (imageCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCode.isExpired()) {
            validateCodeStore.remove(request, getValidateSeesionKey());
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(imageCode.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        validateCodeStore.remove(request, getValidateSeesionKey());
    }


    /**
     * 获取对应的session的key
     */
    protected abstract String getValidateSeesionKey();

    /**
     * 获取需要校验的请求字段的值
     */
    protected abstract String getValidateParameterName();
}

