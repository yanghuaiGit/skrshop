package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.common.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ValidateCode implements Serializable {

    // 验证码
    private String code = null;
    //过期时间
    private LocalDateTime expireTime;

    public ValidateCode() {
    }

    public ValidateCode(String code, Long expirin) {
        this.code = code;
        this.expireTime = DateUtil.DateGet.currentDateTime().plusSeconds(expirin);
    }


    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
}
