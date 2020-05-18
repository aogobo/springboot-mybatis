package com.samchen.gorun.mapper;

import com.samchen.gorun.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
    List<User> findByName(String name);
}