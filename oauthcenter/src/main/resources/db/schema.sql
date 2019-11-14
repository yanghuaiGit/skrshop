create table auth_item
(
  auth_item_id int(11) unsigned auto_increment
  comment '自增id'
    primary key,
  ms_id        tinyint(11) unsigned default '0'   not null
  comment '系统id',
  menu_id      varchar(255) default '0'           not null
  comment '页面/接口uri',
  create_at    datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  create_by    int default '0'                    not null
  comment '创建人staff_id',
  update_at    datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  update_by    int default '0'                    not null
  comment '修改人staff_id',
  status       tinyint(1) default '0'             not null
  comment '状态 1:enable, 0:disable, -1:deleted'
)
  comment '系统权限'
  engine = InnoDB
  charset = utf8mb4;

create index idx_ms_menu
  on auth_item (ms_id, menu_id);

create table auth_ms
(
  auth_ms_id smallint(11) unsigned auto_increment
  comment '自增id'
    primary key,
  ms_name    varchar(255) default '0'           not null
  comment '系统名称',
  ms_desc    varchar(255) default '0'           not null
  comment '系描述',
  ms_domain  varchar(255) default '0'           not null
  comment '系统域名',
  create_at  datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  create_by  int default '0'                    not null
  comment '创建人staff_id',
  update_at  datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  modify_at  int default '0'                    not null
  comment '修改人staff_id',
  status     tinyint(1) default '0'             not null
  comment '状态 1:enable, 0:disable, -1:deleted'
)
  comment '系统map(登记目前存在的后台系统信息)'
  engine = InnoDB
  charset = utf8mb4;

create index idx_domain
  on auth_ms (ms_domain);

create table auth_ms_menu
(
  auth_ms_menu_id int(11) unsigned auto_increment
  comment '自增id'
    primary key,
  ms_id           smallint(11) unsigned default '0'  not null
  comment '系统id',
  parent_id       int(11) unsigned default '0'       not null
  comment '父菜单id',
  menu_name       varchar(255) default '0'           not null
  comment '菜单名称',
  menu_desc       varchar(255) default '0'           not null
  comment '菜描述',
  menu_uri        varchar(255) default '0'           not null
  comment '菜单uri',
  create_at       datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  create_by       int default '0'                    not null
  comment '创建人staff_id',
  update_at       datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  update_by       int default '0'                    not null
  comment '修改人staff_id',
  status          tinyint(1) default '0'             not null
  comment '状态 1:enable, 0:disable, -1:deleted'
)
  comment '系统menu'
  engine = InnoDB
  charset = utf8mb4;

create index idx_ms_id
  on auth_ms_menu (ms_id);

create index idx_parent_id
  on auth_ms_menu (parent_id);

create table auth_role
(
  auth_role_id  int(11) unsigned auto_increment
  comment '自增id'
    primary key,
  name          varchar(255) default '0'           not null
  comment '角色名称''权限集合 多个值,号隔开'',',
  `desc`        varchar(255) default '0'           not null
  comment '角描述',
  auth_item_set text                               null,
  create_at     datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  create_by     int default '0'                    not null
  comment '创建人staff_id',
  update_at     datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  update_by     int default '0'                    not null
  comment '修改人staff_id',
  status        tinyint(1) default '0'             not null
  comment '状态 1:enable, 0:disable, -1:deleted'
)
  comment '员工角色'
  engine = InnoDB
  charset = utf8mb4;

create table auth_role_staff
(
  auth_role_staff_id int(11) unsigned auto_increment
  comment '自增id'
    primary key,
  staff_id           int(11) unsigned default '0'       not null
  comment '员工id',
  role_set           text                               null
  comment '角色集合 多个值,号隔开',
  create_at          datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  create_by          int default '0'                    not null
  comment '创建人staff_id',
  update_at          datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  modify_at          int default '0'                    not null
  comment '修改人staff_id',
  status             tinyint(1) default '0'             not null
  comment '状态 1:enable, 0:disable, -1:deleted'
)
  comment '权限角色与员工关系'
  engine = InnoDB
  charset = utf8mb4;

create index idx_staff_id
  on auth_role_staff (staff_id);

create table staff_info
(
  staff_id  int(11) unsigned auto_increment
  comment '员工id'
    primary key,
  uid       int(11) unsigned default '0'       not null
  comment '账号id',
  email     varchar(30) default ''             not null
  comment '员工邮箱',
  phone     varchar(15) default ''             not null
  comment '员工手机号',
  name      varchar(30) default ''             not null
  comment '员工姓名',
  nick_name varchar(30) default ''             not null
  comment '员工昵称',
  avatar    varchar(255) default ''            not null
  comment '员工头像(相对路径)',
  gender    tinyint(1) default '0'             not null
  comment '员工性别(1 ''male'', 2 ''female'', 0''unknow'')',
  create_at datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  update_at datetime default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '更新时间',
  constraint staff_info_staff_id_uindex
  unique (staff_id)
)
  comment '员工信息(这里列了大概的信息，多的可以垂直拆表)'
  engine = InnoDB
  charset = utf8mb4;

create index idx_email
  on staff_info (email);

create index idx_phone
  on staff_info (phone);

create index idx_uid
  on staff_info (uid);

