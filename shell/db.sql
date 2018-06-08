CREATE TABLE `t_event_trace` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_name` varchar(30) NOT NULL DEFAULT '' COMMENT '调用模块名称',
  `event_name` varchar(200) DEFAULT NULL COMMENT '事件',
  `trace_key` varchar(100) DEFAULT NULL COMMENT '跟踪标示，如用户id',
  `thread_id` varchar(20) DEFAULT NULL,
  `trace_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `t_trace_cfg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `model_name` varchar(50) NOT NULL DEFAULT '',
  `trace_key` varchar(100) DEFAULT NULL,
  `is_on` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE USER 'trace_user01'@'%' IDENTIFIED BY 'zhIfp/MQwxm54a94';
grant all privileges on trace_db.* to 'trace_user01'@'%';
flush privileges;
