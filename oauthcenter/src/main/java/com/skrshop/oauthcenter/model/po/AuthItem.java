package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthItem implements Serializable {
    /**
     * 自增ID
     */
    private Long authItemId;
    /**
     * 系统ID
     */
    private Long msId;
    /**
     * 接口ID
     */
    private Long menuId;
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
     * 1启用 2未启用 3 已删除
     */
    private Integer status;

}
