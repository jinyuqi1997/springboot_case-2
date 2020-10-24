package com.atguigu.controller;


import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;



    @ResponseBody
    @RequestMapping("/user/findAll")
    public Result selectAll(){
        try {
            List<User> users = userService.selectAll();
            return Result.ok(true , "成功" , users);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(false ,"失败");
        }
    }
}
