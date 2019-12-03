package com.skrshop.securitycore.validate.code.sms;

import com.skrshop.common.utils.DateUtil;
import com.skrshop.securitycore.validate.ValidateCode;
import com.skrshop.securitycore.validate.ValidateCodeGenerator;
import com.skrshop.securitycore.validate.ValidateCodeStore;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;


@AllArgsConstructor
public class SmsValidateCodeStore implements ValidateCodeStore<ValidateCode> {

    private RedisTemplate<String, Object> redisTemplate;
    private ValidateCodeGenerator smsCodeGenerator;

    @Override
    public void save(ServletWebRequest request, String key, ValidateCode validateCode) {
        long timeout = DateUtil.DateCompare.untilMinutes(DateUtil.DateGet.currentDateTime(), validateCode.getExpireTime());
        this.redisTemplate.opsForValue().set(key, validateCode.getCode(), timeout, TimeUnit.SECONDS);
    }

    @Override
    public ValidateCode getCode(ServletWebRequest request, String key) {
        return (ValidateCode) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean remove(ServletWebRequest request, String key) {
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.delete(key);
        }
        return true;

    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        return smsCodeGenerator.generate(request);
    }
}
