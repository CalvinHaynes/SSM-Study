CREATE TABLE `blog`
(
    `id`          int(10)     NOT NULL AUTO_INCREMENT COMMENT '博客id',
    `title`       varchar(30) NOT NULL COMMENT '博客标题',
    `author`      varchar(30) NOT NULL COMMENT '博客作者',
    `create_time` datetime(0) NOT NULL COMMENT '创建时间',
    `views`       int(30)     NOT NULL COMMENT '浏览量',
    PRIMARY KEY (`id`)
)