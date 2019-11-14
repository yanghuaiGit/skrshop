package com.skrshop.oauthcenter.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StaffInfo implements Serializable {
 private Long staffId;
 private Long uid;
 private String email;
 private String phone;
 private String name;
 private String nickName;
 private String avatar;
 private Integer gender;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 更新时间
     */
    private LocalDateTime updateAt;
}
