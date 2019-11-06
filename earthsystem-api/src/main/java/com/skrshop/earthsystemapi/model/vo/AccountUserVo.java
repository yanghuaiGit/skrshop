package com.skrshop.earthsystemapi.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Data
public class AccountUserVo  implements Serializable {

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
