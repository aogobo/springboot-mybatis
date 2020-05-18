package com.samchen.gorun.service;

import com.samchen.gorun.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findByName(String name);
}
