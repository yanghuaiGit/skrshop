-- 账户模型
CREATE TABLE `account_user`
(
    `id`               int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '账号id',
    `type`             tinyint(1)       NOT NULL DEFAULT '0' COMMENT '账户类型 0:员工,1:用户',
    `orign`            tinyint(1)       NOT NULL DEFAULT '0' COMMENT '账户来源 0:skr电商,1:sky新闻客户端',
    `email`            varchar(30)      NOT NULL DEFAULT '' COMMENT '邮箱',
    `phone`            varchar(15)      NOT NULL DEFAULT '' COMMENT '手机号',
    `username`         varchar(30)      NOT NULL DEFAULT '' COMMENT '用户名',
    `password`         varchar(32)      NOT NULL DEFAULT '' COMMENT '密码',
    `create_at`        int(13)          NOT NULL DEFAULT '0' COMMENT '创建时间',
    `create_ip_at`     varchar(12)      NOT NULL DEFAULT '' COMMENT '创建ip',
    `last_login_at`    int(13)          NOT NULL DEFAULT '0' COMMENT '最后一次登录时间',
    `last_login_ip_at` varchar(12)      NOT NULL DEFAULT '' COMMENT '最后一次登录ip',
    `login_times`      int(11)          NOT NULL DEFAULT '0' COMMENT '登录次数',
    `status`           tinyint(1)       NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_email` (`email`),
    KEY `idx_phone` (`phone`),
    KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='账户';

-- 第三方账户
CREATE TABLE `account_platform`
(
    `id`             int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `uid`            int(11) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `platform_id`    varchar(60)      NOT NULL DEFAULT '' COMMENT '平台id',
    `platform_token` varchar(60)      NOT NULL DEFAULT '' COMMENT '平台access_token',
    `type`           tinyint(1)       NOT NULL DEFAULT '0' COMMENT '平台类型 0:未知,1:facebook,2:google,3:wechat,4:qq,5:weibo,6:twitter',
    `nickname`       varchar(60)      NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`         varchar(255)     NOT NULL DEFAULT '' COMMENT '头像',
    `create_at`      int(13)          NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`      int(13)          NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`),
    KEY `idx_platform_id` (`platform_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='第三方用户信息';

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
  DEFAULT CHARSET = utf8mb4 COMMENT ='账户信息';

-- 员工表
CREATE TABLE `staff_info`
(
    `id`        int(11) unsigned                NOT NULL AUTO_INCREMENT COMMENT '员工id',
    `uid`       int(11) unsigned                NOT NULL DEFAULT '0' COMMENT '账号id',
    `email`     varchar(30)                     NOT NULL DEFAULT '' COMMENT '员工邮箱',
    `phone`     varchar(15)                     NOT NULL DEFAULT '' COMMENT '员工手机号',
    `name`      varchar(30)                     NOT NULL DEFAULT '' COMMENT '员工姓名',
    `nickname`  varchar(30)                     NOT NULL DEFAULT '' COMMENT '员工昵称',
    `avatar`    varchar(255)                    NOT NULL DEFAULT '' COMMENT '员工头像(相对路径)',
    `gender`    enum ('male','female','unknow') NOT NULL DEFAULT 'unknow' COMMENT '员工性别',
    `create_at` int(13)                         NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at` int(13)                         NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_uid` (`uid`),
    KEY `idx_email` (`email`),
    KEY `idx_phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='员工信息(这里列了大概的信息，多的可以垂直拆表)';

-- 权限管理: 系统map
CREATE TABLE `auth_ms`
(
    `id`        smallint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `ms_name`   varchar(255)          NOT NULL DEFAULT '0' COMMENT '系统名称',
    `ms_desc`   varchar(255)          NOT NULL DEFAULT '0' COMMENT '系描述',
    `ms_domain` varchar(255)          NOT NULL DEFAULT '0' COMMENT '系统域名',
    `create_at` int(13)               NOT NULL DEFAULT '0' COMMENT '创建时间',
    `create_by` int(11)               NOT NULL DEFAULT '0' COMMENT '创建人staff_id',
    `update_at` int(13)               NOT NULL DEFAULT '0' COMMENT '更新时间',
    `update_by` int(11)               NOT NULL DEFAULT '0' COMMENT '修改人staff_id',
    `status`    tinyint(1)            NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_domain` (`ms_domain`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统map(登记目前存在的后台系统信息)';

-- 权限管理: 系统menu
CREATE TABLE `auth_ms_menu`
(
    `id`        int(11) unsigned      NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `ms_id`     smallint(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统id',
    `parent_id` int(11) unsigned      NOT NULL DEFAULT '0' COMMENT '父菜单id',
    `menu_name` varchar(255)          NOT NULL DEFAULT '0' COMMENT '菜单名称',
    `menu_desc` varchar(255)          NOT NULL DEFAULT '0' COMMENT '菜描述',
    `menu_uri`  varchar(255)          NOT NULL DEFAULT '0' COMMENT '菜单uri',
    `create_at` int(13)               NOT NULL DEFAULT '0' COMMENT '创建时间',
    `is_show`   enum ('yes','no')     NOT NULL DEFAULT 'no' COMMENT '是否展示菜单',
    `create_by` int(11)               NOT NULL DEFAULT '0' COMMENT '创建人staff_id',
    `update_at` int(13)               NOT NULL DEFAULT '0' COMMENT '更新时间',
    `update_by` int(11)               NOT NULL DEFAULT '0' COMMENT '修改人staff_id',
    `status`    tinyint(1)            NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_ms_id` (`ms_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统menu';

-- 权限管理: 系统权限
CREATE TABLE `auth_item`
(
    `id`        int(11) unsigned     NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `ms_id`     tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统id',
    `menu_id`   varchar(255)         NOT NULL DEFAULT '0' COMMENT '页面/接口uri',
    `create_at` int(13)              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `create_by` int(11)              NOT NULL DEFAULT '0' COMMENT '创建人staff_id',
    `update_at` int(13)              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `update_by` int(11)              NOT NULL DEFAULT '0' COMMENT '修改人staff_id',
    `status`    tinyint(1)           NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_ms_menu` (`ms_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统权限';

-- 权限管理: 系统权限(权限的各个集合)
CREATE TABLE `auth_role`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`          varchar(255)     NOT NULL DEFAULT '0' COMMENT '角色名称',
    `desc`          varchar(255)     NOT NULL DEFAULT '0' COMMENT '角描述',
    `auth_item_set` text COMMENT '权限集合 多个值,号隔开',
    `create_at`     int(13)          NOT NULL DEFAULT '0' COMMENT '创建时间',
    `create_by`     int(11)          NOT NULL DEFAULT '0' COMMENT '创建人staff_id',
    `update_at`     int(13)          NOT NULL DEFAULT '0' COMMENT '更新时间',
    `update_by`     int(11)          NOT NULL DEFAULT '0' COMMENT '修改人staff_id',
    `status`        tinyint(1)       NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='员工角色';

-- 权限管理: 角色与员工关系
CREATE TABLE `auth_role_staff`
(
    `id`        int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `staff_id`  int(11) unsigned NOT NULL DEFAULT '0' COMMENT '员工id',
    `role_set`  text COMMENT '角色集合 多个值,号隔开',
    `create_at` int(13)          NOT NULL DEFAULT '0' COMMENT '创建时间',
    `create_by` int(11)          NOT NULL DEFAULT '0' COMMENT '创建人staff_id',
    `update_at` int(13)          NOT NULL DEFAULT '0' COMMENT '更新时间',
    `update_by` int(11)          NOT NULL DEFAULT '0' COMMENT '修改人staff_id',
    `status`    tinyint(1)       NOT NULL DEFAULT '0' COMMENT '状态 1:enable, 0:disable, -1:deleted',
    PRIMARY KEY (`id`),
    KEY `idx_staff_id` (`staff_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限角色与员工关系';