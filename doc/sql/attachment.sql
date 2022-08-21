DROP TABLE IF EXISTS attachment;

CREATE TABLE IF NOT EXISTS attachment
(
    `id`           bigint(11)          NOT NULL AUTO_INCREMENT,
    `updateTime`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `createTime`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`         int                          DEFAULT 0 COMMENT '类型',
    `status`       int                          DEFAULT 1 COMMENT '状态',

    `category`     varchar(100)        NOT NULL COMMENT '附件类型',
    `parent`       bigint(11)                   DEFAULT 0 NOT NULL COMMENT '附件父类ID',
    `path`         varchar(100)        NOT NULL COMMENT '附件绝对地址',
    `name`         varchar(100)        NOT NULL COMMENT '附件名称',
    `attachmentId` varchar(100) UNIQUE NOT NULL COMMENT '附件uid',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='附件表';

