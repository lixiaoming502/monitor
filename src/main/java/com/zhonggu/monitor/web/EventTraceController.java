package com.zhonggu.monitor.web;

import com.zhonggu.monitor.model.EventTrace;
import com.zhonggu.monitor.service.EventTraceService;
import com.zhonggu.monitor.utils.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lixiaoming on 2018/6/8.
 */
@RestController
@RequestMapping("/trace")
public class EventTraceController {

    @Autowired
    private EventTraceService eventTraceService ;

    private static Logger logger = LoggerFactory.getLogger(EventTraceController.class);

    @RequestMapping("/reportme")
    public RespCode reportMe(EventTrace eventTrace) throws Exception {
        eventTraceService.addTraceEvent(eventTrace);
        return RespCode.SUCCESS;
    }

    @RequestMapping("/allowme")
    public String allowMe(@RequestParam("module_name") String moduleName,
                          @RequestParam("trace_key") String traceKey) {
        return ""+eventTraceService.isOn(moduleName,traceKey);
    }


}
