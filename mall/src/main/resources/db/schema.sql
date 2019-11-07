-- 用户模型
CREATE TABLE `skr_member`
(
    `id`        int(11) unsigned                NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `uid`       int(11) unsigned                NOT NULL DEFAULT '0' COMMENT '账号id',
    `nickname`  varchar(30)                     NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`    varchar(255)                    NOT NULL DEFAULT '' COMMENT '头像(相对路径)',
    `gender`      tinyint(1) unsigned  NOT NULL default 0 COMMENT '性别：1男，2女',
    `user_level_id`       bigint(20) not null default 0 comment '用户等级',
    `withdraw_passwd`   varchar(32) not null default '' COMMENT '提现支付密码',
    `register_from`       varchar(64) not null default '' comment '注册来源：android，ios，web',
    `ffreeze_status`       tinyint(1) unsigned not null default 0 comment '冻结状态 ：1正常，2冻结',
    `create_at`         datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   `modify_at`         datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息';

--用户等级表
  drop table if exists skr_user_level;

/*==============================================================*/
/* Table: t_bbc_user_level                                      */
/*==============================================================*/
create table skr_user_level
(
   `user_level_id `      bigint(20) unsigned not null auto_increment comment '用户等级ID',
   `level_name`          varchar(32) not null default '' comment '用户等级名称',
   `level_integral`      bigint(20) unsigned not null default 0 comment '用户等级积分',
   `level_remark`        varchar(255) not null default '' comment '用户等级备注',
   `level_status`        tinyint(1) unsigned not null default 1 comment '等级状态：1正常， 2删除',
   `create_by`           bigint(20) unsigned not null default 0 comment '新增操作人ID',
   `modify_by`           bigint(20) unsigned not null default 0 comment '更新操作人ID',
   `create_at`         datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   `modify_at`         datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (fuser_level_id)
);

alter table skr_user_level comment '用户等级表';
