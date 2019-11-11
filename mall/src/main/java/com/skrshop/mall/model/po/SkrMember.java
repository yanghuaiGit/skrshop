package com.skrshop.mall.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


/**
 * 用户DTO
 */
@Data
public class SkrMember {

    @TableId(value = "id", type = IdType.AUTO)
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
