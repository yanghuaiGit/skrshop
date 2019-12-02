package com.skrshop.securitycore.validate;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码需要的基本类
 */
@Data
public class ValidateCode {
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 多长时间后过期
     *
     * @param code 验证码值
     * @param expireIn 过期时间单位为秒
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }


    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
