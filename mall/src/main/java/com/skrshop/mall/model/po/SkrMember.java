package com.skrshop.mall.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


/**
 * 用户DTO
 */
@Data
public class SkrMember {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private Long uid;
    private String nickName;
    private String avatar;
    private Integer gender;
    private Long userLevelId;
    private String withdrawPasswd;
    private Integer registerFrom;
    private Integer ffreezeStatus;
    private Date createAt;
    private Date modifyAt;

}
