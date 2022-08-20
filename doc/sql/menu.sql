DROP TABLE IF EXISTS menu;

CREATE TABLE IF NOT EXISTS menu
(
    `id`         bigint(11)   NOT NULL AUTO_INCREMENT,
    `updateTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `createTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`       int                   DEFAULT 0 COMMENT '类型',
    `status`     int                   DEFAULT 1 COMMENT '状态',
    `icon`       varchar(100) COMMENT '菜单图标',
    `link`       varchar(100) NOT NULL COMMENT '菜单连接',
    `title`      varchar(100) NOT NULL COMMENT '菜单名称',
    `sort`       int                   DEFAULT 0 COMMENT '菜单序列',
    `parent`     bigint(11)   NOT NULL DEFAULT 0 COMMENT '父级菜单',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='菜单表';