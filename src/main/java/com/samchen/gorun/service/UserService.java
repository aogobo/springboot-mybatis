package com.samchen.gorun.service;

import com.samchen.gorun.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    int addUser(User user);
    List<User> findByName(String name);
    int updateUser(int id);
}
