package com.yeep.service;

import com.yeep.dao.UserDAO;
import com.yeep.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
