package com.yeep.dao;

import com.yeep.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    int deleteUser(int id);
    int insertUser(User user);
    int updateUser(User user);
}
