-- 用户模型
CREATE TABLE `skr_member`
(
    `id`        int(11) unsigned                NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `uid`       int(11) unsigned                NOT NULL DEFAULT '0' COMMENT '账号id',
    `nickname`  varchar(30)                     NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`    varchar(255)                    NOT NULL DEFAULT '' COMMENT '头像(相对路径)',
    `gender`    enum ('male','female','unknow') NOT NULL DEFAULT 'unknow' COMMENT '性别',
    `role`      tinyint(1) unsigned             NOT NULL DEFAULT '0' COMMENT '角色 0:普通用户 1:vip',
    `create_at` int(13)                         NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at` int(13)                         NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息';