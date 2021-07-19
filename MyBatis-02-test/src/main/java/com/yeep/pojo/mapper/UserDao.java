package com.yeep.pojo.mapper;

import com.yeep.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
     List<User> getAllUser();
     int insertUser(User user);
     int deleteUser(int id);
     int updateUser(User user);
     List<User> getUserByName(Map<String,String > map);
}
