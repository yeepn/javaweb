package com.yeep.mapper;

import com.yeep.pojo.User;
import com.yeep.mapper.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("userDao")
public class UserDaoIml implements UserDao {
    @Autowired
    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public List<User> getAllUsers() {
        return sqlSession.getMapper(UserDao.class).getAllUsers();
    }
    @Override
    public User getUser(int id) {
        return sqlSession.getMapper(UserDao.class).getUser(id);
    }
    @Override
    public void deleteUser(int id) {
         sqlSession.getMapper(UserDao.class).deleteUser(id);
    }
    @Override
    public int insertUser(User user) {
        return sqlSession.getMapper(UserDao.class).insertUser(user);
    }
}
