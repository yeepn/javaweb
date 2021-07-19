package com.yeep.service;

import com.yeep.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    int deleteUser(int id);
    int insertUser(User user);
    int updateUser(User user);
}
