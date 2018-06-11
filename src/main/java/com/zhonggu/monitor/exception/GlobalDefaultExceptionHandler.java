package com.zhonggu.monitor.exception;

import com.zhonggu.monitor.utils.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理全局异常
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public RespCode exception(Exception e){
		logger.warn("",e);
		return RespCode.EXCEPTION;
	}
}