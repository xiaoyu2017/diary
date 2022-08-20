DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    `id`         bigint(11)         NOT NULL AUTO_INCREMENT,
    `updateTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `createTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`       int                         DEFAULT 0 COMMENT '类型',
    `status`     int                         DEFAULT 1 COMMENT '状态',
    `name`       varchar(20) UNIQUE NOT NULL COMMENT '用户名',
    `nickName`   varchar(20) UNIQUE COMMENT '昵称',
    `password`   varchar(50)        NOT NULL COMMENT '用户密码',
    `iconLink`   varchar(100) COMMENT '头像',
    `email`      varchar(100) COMMENT '邮箱',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户表';