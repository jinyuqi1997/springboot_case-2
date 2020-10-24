package com.atguigu;


import com.atguigu.dao.UserMapper;
import com.atguigu.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    //@Autowired
    @Resource
    UserMapper userMapper;

    @Test
    public void selectAll(){

        List<User> users = userMapper.selectAll();

        System.out.println("users = " + users);
    }
}
