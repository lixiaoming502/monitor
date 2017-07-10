package com.zhonggu.bluevally.controller;

import com.zhonggu.bluevally.route.TradeRouter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lixiaoming on 2017/7/7.
 */
@Controller
public class ServiceEntry {

    private static Logger logger = LoggerFactory.getLogger(ServiceEntry.class);

    @RequestMapping(value = "/service_entry.do",method = RequestMethod.POST)
    @ResponseBody
    public void entry(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, DecoderException {
        //设置超时时间5分钟
        httpServletRequest.getSession().setMaxInactiveInterval(60*5);
        List<String> contents = IOUtils.readLines(httpServletRequest.getReader());
        String request = contents.get(0);
        logger.info("receive org ["+request+"]");
        String resp = TradeRouter.processRequest(request);
        logger.info("send ["+resp+"]");
        httpServletResponse.getWriter().write(resp);
    }
}
