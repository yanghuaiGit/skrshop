package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRole implements Serializable {
    private Long authRoleId;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;

    /**
     * 权限集合 多个值,号隔开
     */
    private String authItemSet;
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
    /**
     * 1启用 2未启用 3已删除
     */
    private Integer status;

}
