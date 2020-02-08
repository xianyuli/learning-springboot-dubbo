package com.xianyuli.dubbo.bootdubboapi.controller;


import com.xianyuli.dubbo.bootdubboapi.entity.User;
import com.xianyuli.dubbo.bootdubboapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xianyuli
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> users(){
        return userService.queryUsers();
    }

    @GetMapping("/{uid}")
    public User user(@PathVariable("uid") String userId){
        return userService.getById(userId);
    }


}
