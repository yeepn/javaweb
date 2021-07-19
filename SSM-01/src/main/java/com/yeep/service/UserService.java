package com.yeep.service;

import com.yeep.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByid(int id);
    int deleteUser(int id);
    int updateUser(User user);
    int insertUser(User user);
}
