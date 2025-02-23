package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.pojo.User;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    public  List<User> list(){
        List<User>userlist=userService.findAll();


        return  userlist;
    }
}
