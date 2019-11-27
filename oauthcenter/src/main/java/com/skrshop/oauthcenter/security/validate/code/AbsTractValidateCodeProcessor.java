package com.skrshop.oauthcenter.security.validate.code;

import com.alibaba.fastjson.JSON;
import com.skrshop.common.context.RequestHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.IOException;

public abstract class AbsTractValidateCodeProcessor implements ValidateCodeProcessor {

    @Resource
    private ValueOperations<String, String> valueOperations;



    @Override
    public void create() throws Exception {
        ValidateCode validateCode = generate();
        save(validateCode);
        send(validateCode);
    }

    private void save(ValidateCode validateCode) throws IOException {
        valueOperations.set(ValidateCodeProcessor.IMAGECODEKEY + getProcessorType().toUpperCase(),
                JSON.toJSONString(validateCode));
    }


    /**
     * 发送校验码
     *
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ValidateCode validateCode) throws Exception;

    protected abstract ValidateCode generate();

    private String getProcessorType() {
        return StringUtils.substringAfter(RequestHolder.getRequest().getRequestURI(), "/code/");
    }

}
