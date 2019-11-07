package com.skrshop.mall.model.dto;

import lombok.Data;


/**
 * 用户DTO
 */
@Data
public class SkrMemberDto {

    private Long id;
    private Long uid;
    private String nickName;
    private String avatar;
    private Integer gender;
    private Long userLevelId;
    private String withdrawPasswd;
    private Integer registerFrom;
    private Integer ffreezeStatus;
    private  String password;
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户名
     */
    private String username;
}
