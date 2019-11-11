package com.skrshop.earthsystem.model.po.account;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@TableName("account_platform")
public class AccountPlatform implements Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 账号ID
     */
    private Long uid;

    /**
     * 平台id
     */
    private String platformId;

    /**
     * 平台access_token
     */
    private String platformToken;

    /**
     * 平台类型 0:未知,1:facebook,2:google,3:wechat,4:qq,5:weibo,6:twitter
     */
    private Integer type;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 图像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;
}
