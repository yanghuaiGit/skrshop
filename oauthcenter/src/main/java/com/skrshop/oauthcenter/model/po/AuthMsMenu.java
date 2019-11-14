package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthMsMenu implements Serializable {
    private Long authMsMenuId;
    /**
     * 所属系统ID
     */
    private Long msId;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单描述
     */
    private String menuDesc;
    /**
     * 菜单URI
     */
    private String menuUri;
    /**
     * 1启用 2未启用 3 已删除
     */
    private Integer status;
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
