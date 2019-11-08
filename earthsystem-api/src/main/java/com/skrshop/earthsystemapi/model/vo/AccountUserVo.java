package com.skrshop.earthsystemapi.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 */
@Data
public class AccountUserVo implements Serializable {

    /**
     * 账号id
     */
    private Long id;

    /**
     * 账户类型 0:员工,1:用户
     */
    private Integer type;

    /**
     * 账户来源 0:skr电商,1:sky新闻客户端
     */
    private Integer orign;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}
