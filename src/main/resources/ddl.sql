CREATE TABLE `user` (
`id`  int NOT NULL AUTO_INCREMENT ,
`created_at`  datetime NULL ,
`updated_at`  datetime NULL ,
`telphone`  varchar(40) NULL ,
`password`  varchar(200) NULL ,
`nick_name`  varchar(40) NULL ,
`gender`  int NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `telphone_unique_index` (`telphone`) USING BTREE
)
;

CREATE TABLE `dianpingdb`.`seller` (
`id`  int NOT NULL AUTO_INCREMENT ,
`name`  varchar(80) NOT NULL DEFAULT '\"\"' ,
`created_at`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`updated_at`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`remark_score`  decimal(2,1) NOT NULL ,
`disabled_flag`  int NOT NULL DEFAULT 0 COMMENT '0代表启用' ,
PRIMARY KEY (`id`)
)
;
