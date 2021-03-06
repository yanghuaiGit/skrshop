package com.skrshop.oauthcore.config.properties;

import lombok.Data;

@Data
public class TokenCodeProperties {


    /**
     * 是否启用refreshToken
     */
    private Boolean enableRefreshToken = false;


    /**
     * token默认过期时间单位S
     */
    private Integer tokenExpireTime = 30*60;

    /**
     * token默认过期时间单位S
     */
    private Integer refreshTokenExpireTime = 7 * 24 * 60*60;

    /**
     * 用户选择保存登录状态对应token过期时间（天）
     */
//    private Integer saveLoginTime = 7;
//
//    /**
//     * 限制用户登陆错误次数（次）
//     */
//    private Integer loginTimeLimit = 10;
//
//    /**
//     * 错误超过次数后多少分钟后才能继续登录（分钟）
//     */
//    private Integer loginAfterTime = 10;


    /**
     * 使用redis存储token
     */
    private Boolean redis = false;


    /**
     * 是否启用远程验证token
     */
    private Boolean enable = false;

}
