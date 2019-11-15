-- 账户模型
CREATE TABLE `account_user`
(
  `id`               int(11) unsigned NOT NULL AUTO_INCREMENT
  COMMENT '账号id',
  `type`             tinyint(1)       NOT NULL DEFAULT '0'
  COMMENT '账户类型 0:员工,1:用户',
  `orign`            tinyint(1)       NOT NULL DEFAULT '0'
  COMMENT '账户来源 0:skr电商,1:sky新闻客户端',
  `birthday`         int(13)          NOT NULL DEFAULT '0'
  comment '生日',
  `idcard_no`        varchar(20)      not null default ''
  comment '身份证号',
  `fidcard_name`     varchar(255)     not null default ''
  comment '身份证名',
  `email`            varchar(30)      NOT NULL DEFAULT ''
  COMMENT '邮箱',
  `phone`            varchar(15)      NOT NULL DEFAULT ''
  COMMENT '手机号',
  `username`         varchar(30)      NOT NULL DEFAULT ''
  COMMENT '用户名',
  `password`         varchar(32)      NOT NULL DEFAULT ''
  COMMENT '密码',
  `create_at`        datetime         not null default CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `create_ip_at`     varchar(12)      NOT NULL DEFAULT ''
  COMMENT '创建ip',
  `last_login_at`    datetime         not null default CURRENT_TIMESTAMP
  COMMENT '最后一次登录时间',
  `last_login_ip_at` varchar(12)      NOT NULL DEFAULT ''
  COMMENT '最后一次登录ip',
  `login_times`      int(11)          NOT NULL DEFAULT '0'
  COMMENT '登录次数',
  `status`           tinyint(1)       NOT NULL DEFAULT '0'
  COMMENT '状态 1:enable, 0:disable, -1:deleted',
  PRIMARY KEY (`id`),
  KEY `idx_email` (`email`),
  KEY `idx_phone` (`phone`),
  KEY `idx_username` (`username`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='账户';

-- 第三方账户
CREATE TABLE `account_platform`
(
  `id`             int(11) unsigned NOT NULL AUTO_INCREMENT
  COMMENT '自增id',
  `uid`            int(11) unsigned NOT NULL DEFAULT '0'
  COMMENT '账号id',
  `platform_id`    varchar(60)      NOT NULL DEFAULT ''
  COMMENT '平台id',
  `platform_token` varchar(60)      NOT NULL DEFAULT ''
  COMMENT '平台access_token',
  `type`           tinyint(1)       NOT NULL DEFAULT '0'
  COMMENT '平台类型 0:未知,1:facebook,2:google,3:wechat,4:qq,5:weibo,6:twitter',
  `nickname`       varchar(60)      NOT NULL DEFAULT ''
  COMMENT '昵称',
  `avatar`         varchar(255)     NOT NULL DEFAULT ''
  COMMENT '头像',
  `create_at`      datetime         not null default CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `update_at`      datetime         not null default CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_platform_id` (`platform_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='第三方用户信息';
