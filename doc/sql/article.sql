DROP TABLE IF EXISTS article;

CREATE TABLE IF NOT EXISTS article
(
    `id`          bigint(11)   NOT NULL AUTO_INCREMENT,
    `updateTime`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `createTime`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`        int                   DEFAULT 0 COMMENT '类型',
    `status`      int                   DEFAULT 1 COMMENT '状态',
    `title`       varchar(100) NOT NULL COMMENT '文章标题',
    `tag`         varchar(100) NOT NULL COMMENT '文章标签',
    `content`     text         NOT NULL COMMENT '文章内容',
    `description` varchar(255) NOT NULL COMMENT '文章简介',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='文章表';