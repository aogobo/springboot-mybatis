package com.samchen.gorun.controller;

import com.samchen.gorun.entity.User;
import com.samchen.gorun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/findUser")
    public List<User> findAllUser(@RequestParam String name){
        return userService.findByName(name);
    }

    @RequestMapping("/find")
    public List<User> findUser(@RequestBody User user){
        return userService.findByName(user.getUsername());
    }

}