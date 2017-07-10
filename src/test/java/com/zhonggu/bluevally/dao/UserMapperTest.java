package com.zhonggu.bluevally.dao;

import com.zhonggu.bluevally.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by lixiaoming on 2017/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Test
    public void contextLoads() {
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("lixm1237");
        user.setPasswd("1234567");
        //编程式事物
        transactionTemplate.execute(transactionStatus -> {
            int rows = userMapper.insert(user);
            if(rows==1){
                System.out.println("#############################rows="+rows);
                transactionStatus.setRollbackOnly();
            }
            return null;
        });

        System.out.println("done!");
    }
}
