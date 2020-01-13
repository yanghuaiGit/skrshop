package com.skrshop.securitycore.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CodeProperties {

    //验证码过期时间 s 默认是5分钟
    private int expireIn = 300;

    // 验证码字符个数
    private int length = 6;

    //是否启用
    private Boolean enable = false;

    //需要验证码拦截的url以,隔开 后面应该是map这样就可以restfel形式 url+method
    private List<String> url;

    public CodeProperties(int length) {
        this.length = length;
    }
}
