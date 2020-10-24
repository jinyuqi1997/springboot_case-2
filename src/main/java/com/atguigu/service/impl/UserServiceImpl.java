package com.atguigu.service.impl;


import com.atguigu.dao.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(readOnly = true ,propagation = Propagation.SUPPORTS)//只读事务，支持所有
    public List<User> selectAll() {

        //从缓存中查询数据，规定存储用户信息使用string类型进行存储，存储的key就是userList
        List<User> userList = (List<User>) redisTemplate.boundValueOps("userList").get();

        //如果缓存中没有数据，查询数据库，将查询到的数据放入缓存中
        if (userList==null){
            userList = userMapper.selectAll();
            redisTemplate.boundValueOps("userList").set(userList);
            System.out.println("从数据库中查询数据！");
        }else {

            System.out.println("从缓存中查询数据！！");
        }

        //如果缓存中有数据，就直接返回数据
        return userList;
    }
}
