CREATE DATABASE `db_exam`;

USE `db_exam`;

CREATE TABLE `major` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `pid` int unsigned NOT NULL DEFAULT 0 COMMENT '父节点id',
  `pids` varchar(255) NOT NULL DEFAULT '' COMMENT '所有父节点ID，逗号分隔，从根节点到子节点有序',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '专业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业';

CREATE TABLE `exam_pool` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '题库名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `tenant_id` int unsigned NOT NULL DEFAULT 0 COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库';

CREATE TABLE `question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `major_id` int unsigned NOT NULL DEFAULT 0 COMMENT '专业id',
  `question_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '题型',
  `difficulty_level` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '难度',
  `question_stem` varchar(500) NOT NULL DEFAULT '' COMMENT '题干JSON',
  `options` varchar(500) NOT NULL DEFAULT '' COMMENT '客观题选项JSON',
  `right_answer` varchar(500) NOT NULL DEFAULT '' COMMENT '客观题正确答案',
  `answer_analysis` varchar(500) NOT NULL DEFAULT '' COMMENT '答案解析',
  `tags` varchar(255) NOT NULL DEFAULT '' COMMENT '标签，逗号分隔',
  `pool_id` int unsigned NOT NULL DEFAULT 0 COMMENT '题库ID',
  KEY `idx_question_bankid` (`pool_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目';

CREATE TABLE `exam_paper` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '试卷名称',
  `total_score` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '总分',
  `question_number` int unsigned NOT NULL DEFAULT 0 COMMENT '题目数量',
  `question_mode` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '选题方式',
  `exam_second` int unsigned NOT NULL DEFAULT 0 COMMENT '考试时间（秒）,0不限制',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `tenant_id` int unsigned NOT NULL DEFAULT 0 COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷';

CREATE TABLE `score_rule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `paper_id` int unsigned NOT NULL DEFAULT 0 COMMENT '试卷id',
  `question_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '题型',
  `score` int unsigned NOT NULL DEFAULT 0 COMMENT '每道题分数/题目总分，配合分数算法使用',
  `score_algorithm_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '考试分数算法类型',
  KEY `idx_scorerule_paperid`(`paper_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评分规则';

CREATE TABLE `question_rule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `paper_id` int unsigned NOT NULL DEFAULT 0 COMMENT '试卷id',
  `pool_id` int unsigned NOT NULL DEFAULT 0 COMMENT '题库id',
  `question_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '题型',
  `number` int unsigned NOT NULL DEFAULT 0 COMMENT '选题个数',
  KEY `idx_questionrule_paperid`(`paper_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='随机选题规则';

CREATE TABLE `exam_question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `paper_id` int unsigned NOT NULL DEFAULT 0 COMMENT '试卷id',
  `question_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '题型',
  `question_id` int unsigned NOT NULL DEFAULT 0 COMMENT '题目id',
  KEY `idx_examquestion_paperid`(`paper_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手动组卷考试题目';

CREATE TABLE `exam_scene` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '场景名称',
  `object_type` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '对象类型',
  `object_value` varchar(100) NOT NULL DEFAULT '' COMMENT '对象值',
  `paper_id` int unsigned NOT NULL DEFAULT 0 COMMENT '试卷id',
  `continues` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '是否支持需考',
  KEY `idx_examscene_objecttype_objectvalue`(`object_type`, `object_value`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试场景';

CREATE TABLE `exam_record` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `scene_id` int unsigned NOT NULL DEFAULT 0 COMMENT '考试场景',
  `paper_id` int unsigned NOT NULL DEFAULT 0 COMMENT '试卷id',
  `title` varchar(32) NOT NULL DEFAULT '' COMMENT '试卷名称',
  `total_score` int unsigned NOT NULL DEFAULT 0 COMMENT '总分',
  `exam_second` int unsigned NOT NULL DEFAULT 0 COMMENT '考试时间（秒）,0不限制',
  `user_id` int unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `user_score` int unsigned NOT NULL DEFAULT 0 COMMENT '用户分数',
  `submit_time` timestamp NULL COMMENT '提交时间',
  `tenant_id` int unsigned NOT NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试记录';

CREATE TABLE `exam_detail` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `exam_record_id` int unsigned NOT NULL DEFAULT 0 COMMENT '考试记录id',
  `exam_modules` text NULL COMMENT '试卷组成模块JSON',
  `user_answers` text NULL COMMENT '用户答案JSON',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_examdetail_examrecordid`(`exam_record_id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='考试详情';

