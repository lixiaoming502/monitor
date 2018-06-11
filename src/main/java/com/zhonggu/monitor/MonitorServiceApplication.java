package com.zhonggu.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zhonggu.monitor.dao")
public class MonitorServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(MonitorServiceApplication.class);

	public static void main(String[] args) {
		logger.info("start ----");
		SpringApplication.run(MonitorServiceApplication.class, args);
	}
}
