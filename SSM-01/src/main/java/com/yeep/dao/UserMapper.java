package com.yeep.dao;

import com.yeep.pojo.User;

import java.util.List;
public interface UserMapper {
    List<User> getAllUsers();
    User getUserByid(int id);
    int deleteUser(int id);
    int updateUser(User user);
    int insertUser(User user);
}
