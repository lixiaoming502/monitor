package com.zhonggu.monitor;

import com.zhonggu.monitor.dao.EventTraceMapper;
import com.zhonggu.monitor.model.EventTrace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.zhonggu.monitor.dao")
public class UserServiceApplicationTests {

	@Autowired
	private EventTraceMapper eventTraceMapper ;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		EventTrace record = new EventTrace();
		eventTraceMapper.insert(record);
	}

}
