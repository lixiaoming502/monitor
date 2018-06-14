CREATE TABLE `t_event_trace` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_name` varchar(30) NOT NULL DEFAULT '' COMMENT '调用模块名称',
  `event_name` varchar(200) DEFAULT NULL COMMENT '事件',
  `trace_key` varchar(100) DEFAULT NULL COMMENT '跟踪标示，如用户id',
  `event_stage` int(11) DEFAULT NULL COMMENT '0:开始 1：完成 2：进入',
  `thread_id` varchar(20) DEFAULT NULL,
  `trace_data` varchar(100) DEFAULT NULL COMMENT '提交的数据',
  `trace_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

##分析
select a.module_name,a.`event_name`,a.trace_key,a.st,a.ed,TIMESTAMPDIFF(SECOND,a.st,a.ed) as dif
from (
       select p.module_name,p.`event_name`,p.trace_key,max(p.start_t) as st,max(p.end_t) as ed from
         (
           select module_name,event_name,trace_key,
             (case event_stage when 0 then trace_time else 0 end ) start_t,
             (case event_stage when 1 then trace_time else 0 end ) end_t
           from t_event_trace
         ) p group by p.`module_name`,p.`event_name`,p.`trace_key`
     ) a

#分析2
select a.module_name,a.`event_name`,a.trace_key,a.`trace_data`,a.st,a.ed,TIMESTAMPDIFF(SECOND,a.st,a.ed) as dif
from (
       select p.module_name,p.`event_name`,p.trace_key,p.`trace_data`,max(p.start_t) as st,max(p.end_t) as ed from
         (
           select module_name,event_name,trace_key,trace_data,
             (case event_stage when 0 then trace_time else 0 end ) start_t,
             (case event_stage when 1 then trace_time else 0 end ) end_t
           from t_event_trace
         ) p group by p.`module_name`,p.`event_name`,p.`trace_key`,p.`trace_data`
     ) a