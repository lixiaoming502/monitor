package com.zhonggu.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhonggu.monitor.dao")
public class MonitorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorServiceApplication.class, args);
	}
}
