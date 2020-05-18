package com.samchen.gorun.service.impl;

import com.samchen.gorun.entity.User;
import com.samchen.gorun.mapper.UserMapper;
import com.samchen.gorun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userMapper.findByName(name);
    }
}