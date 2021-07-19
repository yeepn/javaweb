package com.yeep.service;

import com.yeep.mapper.UserDao;
import com.yeep.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyService {
    @Autowired
    private final UserDao userDao;

    public MyService(UserDao userDao) {
        this.userDao = userDao;
    }
    @Test
    public void getAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        allUsers.forEach(x->{
            System.out.println(x);
        });
    }
}
