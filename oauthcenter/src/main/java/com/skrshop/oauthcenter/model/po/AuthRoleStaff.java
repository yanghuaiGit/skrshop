package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRoleStaff implements Serializable {
    private Long authRoleStaffId;

    /**
     * 员工ID
     */
    private Long staffId;

    /**
     * 角色集合 多个值,号隔开
     */
    private String roleSet;

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
     * 1启用 2冻结
     */
    private Integer status;
}
