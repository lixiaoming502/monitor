package com.zhonggu.bluevally.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:spring-ds.xml"})
public class ConfigClass {
    
}