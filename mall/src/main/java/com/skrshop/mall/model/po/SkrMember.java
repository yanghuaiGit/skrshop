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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Long userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getWithdrawPasswd() {
        return withdrawPasswd;
    }

    public void setWithdrawPasswd(String withdrawPasswd) {
        this.withdrawPasswd = withdrawPasswd;
    }

    public Integer getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(Integer registerFrom) {
        this.registerFrom = registerFrom;
    }

    public Integer getFfreezeStatus() {
        return ffreezeStatus;
    }

    public void setFfreezeStatus(Integer ffreezeStatus) {
        this.ffreezeStatus = ffreezeStatus;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}
