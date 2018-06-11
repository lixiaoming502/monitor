package com.zhonggu.monitor.service;

import com.zhonggu.monitor.dao.EventTraceMapper;
import com.zhonggu.monitor.dao.TraceCfgMapper;
import com.zhonggu.monitor.model.EventTrace;
import com.zhonggu.monitor.model.TraceCfg;
import com.zhonggu.monitor.model.TraceCfgExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixiaoming on 2018/6/11.
 */
@Service
public class EventTraceService {

    @Autowired
    private EventTraceMapper eventTraceMapper ;

    @Autowired
    private TraceCfgMapper traceCfgMapper ;

    public void addTraceEvent(EventTrace eventTrace){
        if(isOn(eventTrace.getModuleName(),eventTrace.getTraceKey())){
            eventTraceMapper.insert(eventTrace);
        }
    }

    public boolean isOn(String moduleName, String traceKey) {
        TraceCfgExample condition = new TraceCfgExample();
        TraceCfgExample.Criteria cr = condition.createCriteria();
        cr.andModelNameEqualTo(moduleName);
        cr.andTraceKeyEqualTo(traceKey);
        List<TraceCfg> rr = traceCfgMapper.selectByExample(condition);
        if(CollectionUtils.isEmpty(rr)){
            return false;
        }else{
            return rr.get(0).getIsOn();
        }
    }
}
