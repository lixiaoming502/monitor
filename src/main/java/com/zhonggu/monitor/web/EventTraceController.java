package com.zhonggu.monitor.web;

import com.zhonggu.monitor.RespCode;
import com.zhonggu.monitor.dao.EventTraceMapper;
import com.zhonggu.monitor.model.EventTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lixiaoming on 2018/6/8.
 */
@RestController
@RequestMapping("/trace")
public class EventTraceController {

    @Autowired
    private EventTraceMapper eventTraceMapper ;

    @RequestMapping("/add")
    public RespCode add(EventTrace eventTrace) {
        //TODO:如果跟踪开关（t_trace_cfg）是关闭的，则忽略
        eventTraceMapper.insert(eventTrace);
        return RespCode.SUCCESS;
    }
}
