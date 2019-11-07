package com.skrshop.mall.model.vo;

import lombok.Data;


/**
 * 用户DTO
 */
@Data
public class SkrMemberVo {

    private Long id;
    private Long uid;
    private String nickName;
    private String avatar;
    private Integer gender;
    private Long userLevelId;
    private String withdrawPasswd;
    private Integer registerFrom;
    private Integer ffreezeStatus;




}
