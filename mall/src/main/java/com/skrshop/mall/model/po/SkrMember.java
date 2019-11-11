package com.skrshop.mall.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * 用户
 */
@Data
@TableName("skr_member")
public class SkrMember {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long uid;
    private String nickName;
    private String avatar;
    private Integer gender;
    private Long userLevelId;
    private String withdrawPasswd;
    private Integer registerFrom;
    private Integer ffreezeStatus;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;

}
