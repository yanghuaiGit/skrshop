package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 系统MAP
 */
public class AuthMs implements Serializable {

    private Long authMsId;
    /**
     * 系统名称
     */
    private String msName;
    /**
     * 系统描述
     */
    private String msDesc;
    /**
     * 系统域名
     */
    private String msDomain;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateAt;
    /**
     * 更新人ID
     */
    private Long updateBy;

}
