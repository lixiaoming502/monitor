package com.zhonggu.monitor.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by lixiaoming on 2018/6/11.
 */
@Configuration
@ComponentScan("com.zhonggu.monitor.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
