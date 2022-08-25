DROP TABLE IF EXISTS api_browse;

CREATE TABLE IF NOT EXISTS api_browse
(
    `id`         bigint(11)   NOT NULL AUTO_INCREMENT,
    `updateTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `createTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`       int                   DEFAULT 0 COMMENT '类型',
    `status`     int                   DEFAULT 1 COMMENT '状态',

    `time`       varchar(10)  NOT NULL COMMENT '年月日拼接',
    `api`        varchar(100) NOT NULL COMMENT 'api连接',
    `number`     bigint(11)   NOT NULL COMMENT '访问次数',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='api访问次数表';