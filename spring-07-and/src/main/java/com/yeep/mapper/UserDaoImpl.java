package com.yeep.mapper;

import com.yeep.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public List<User> getAllUser() {
        return sqlSession.getMapper(UserDao.class).getAllUser();
    }
    @Override
    public User getUserById(int id) {
        return sqlSession.getMapper(UserDao.class).getUserById(id);
    }
    @Override
    public int deleteUserById(int id) {
        return sqlSession.getMapper(UserDao.class).deleteUserById(id);
    }
    @Override
    public int insertUser(User user) {
        return sqlSession.getMapper(UserDao.class).insertUser(user);
    }
    @Override
    public int updateUser(User user) {
        return sqlSession.getMapper(UserDao.class).updateUser(user);
    }
}
