USE dili_assistant;

-- --------------------------------------------------------------------
-- 字典配置表
-- 分组(group_code)管理参数配置，系统参数不允许用户编辑修改
-- --------------------------------------------------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `type` TINYINT UNSIGNED NOT NULL COMMENT '类型-系统/用户参数',
    `group_code` VARCHAR(40) NOT NULL COMMENT '组编码',
    `code` VARCHAR(40) NOT NULL COMMENT '参数编码',
    `name` VARCHAR(80) COMMENT '参数名称',
    `value` VARCHAR(1024) NOT NULL COMMENT '参数值',
    `description` VARCHAR(200) COMMENT '备注',
    `created_time` DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_data_dictionary_code` (`group_code`, `code`) USING BTREE
) ENGINE=InnoDB;

-- --------------------------------------------------------------------
-- 系统ID生成器数据模型
-- --------------------------------------------------------------------
DROP TABLE IF EXISTS `uid_sequence_key`;
CREATE TABLE `uid_sequence_key` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `key` VARCHAR(40) NOT NULL COMMENT 'KEY标识',
    `name` VARCHAR(80) NOT NULL COMMENT 'KEY名称',
    `value` BIGINT NOT NULl COMMENT '起始值',
    `step` TINYINT UNSIGNED NOT NULL COMMENT '步长',
    `pattern` VARCHAR(60) COMMENT 'ID格式',
    `expired_on` DATE COMMENT '有效日期',
    `version` BIGINT NOT NULL COMMENT '数据版本',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sequence_key_key` (`key`) USING BTREE
) ENGINE=InnoDB;

-- --------------------------------------------------------------------
-- 文件存储服务数据模型
-- --------------------------------------------------------------------
DROP TABLE IF EXISTS `dfs_file_repository`;
CREATE TABLE `dfs_file_repository` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `repository_id` VARCHAR(40) NOT NULL COMMENT '仓库ID',
    `name` VARCHAR(80) COMMENT '名称',
    `pipeline` TINYINT UNSIGNED NOT NULL COMMENT '服务通道',
    `description` VARCHAR(200) COMMENT '备注',
    `created_time` DATETIME COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_file_repository_id` (`repository_id`) USING BTREE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `dfs_file_object`;
CREATE TABLE `dfs_file_object` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `repository_id` VARCHAR(40) NOT NULL COMMENT '仓库ID',
    `pipeline` TINYINT UNSIGNED NOT NULL COMMENT '服务通道',
    `file_id` VARCHAR(40) NOT NULL COMMENT '文件ID',
    `file_name` VARCHAR(80) DEFAULT NULL COMMENT '文件名称',
    `mime_type` VARCHAR(40) NOT NULL COMMENT 'MIME类型',
    `hits` INTEGER UNSIGNED  NOT NULL COMMENT '访问次数',
    `state` TINYINT UNSIGNED NOT NULL COMMENT '文件状态',
    `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `modified_time` DATETIME DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_file_object_fileId` (`file_id`) USING BTREE
) ENGINE=InnoDB;

-- --------------------------------------------------------------------
-- SMS服务数据模型
-- --------------------------------------------------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `template_id` VARCHAR(40) NOT NULL COMMENT '模版ID',
    `pipeline` TINYINT UNSIGNED NOT NULL COMMENT '服务通道',
    `type` TINYINT UNSIGNED NOT NULL COMMENT '模版类型',
    `name` VARCHAR(80) NOT NULL COMMENT '模版名称',
    `content` VARCHAR(2000) NOT NULL COMMENT '模版内容',
    `state` TINYINT UNSIGNED NOT NULL COMMENT '模版状态',
    `description` VARCHAR(200) COMMENT '备注',
    `out_template_id` VARCHAR(80) COMMENT '外部模版ID', -- 服务通道的模版编号
    `created_time` DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_template_id` (`template_id`) USING BTREE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `sms_message`;
CREATE TABLE `sms_message` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `template_id` VARCHAR(40) NOT NULL COMMENT '模版ID',
    `pipeline` TINYINT UNSIGNED NOT NULL COMMENT '服务通道',
    `type` TINYINT UNSIGNED NOT NULL COMMENT '短信类型',
    `message_id` VARCHAR(40) NOT NULL COMMENT '消息ID',
    `telephone` VARCHAR(20) NOT NULL COMMENT '电话号码',
    `content` VARCHAR(2000) NOT NULL COMMENT '消息内容',
    `state` TINYINT UNSIGNED NOT NULL COMMENT '消息状态',
    `out_message_id` VARCHAR(80) COMMENT '外部消息ID', -- 服务通道的消息编号
    `created_time` DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_message_id` (`message_id`) USING BTREE,
    KEY `idx_message_templateId` (`template_id`) USING BTREE,
    KEY `idx_message_telephone` (`telephone`) USING BTREE
) ENGINE=InnoDB;