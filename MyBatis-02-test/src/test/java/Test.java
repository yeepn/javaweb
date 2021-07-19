import com.yeep.pojo.User;
import com.yeep.pojo.mapper.UserDao;
import com.yeep.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void getAllUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(x->{
            System.out.println(x);
        });

        sqlSession.close();
    }
    @org.junit.Test
    public void insertUser(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User qw = new User(1, "qw", "123456");
        System.out.println(mapper.insertUser(qw));
        sqlSession.commit();
        sqlSession.close();
    }
    @org.junit.Test
    public void deleteUser(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        System.out.println(mapper.deleteUser(1));
        sqlSession.commit();
        sqlSession.close();
    }
    @org.junit.Test
    public void updateUser(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User qw = new User(1, "www", "123456");
        mapper.updateUser(qw);
        sqlSession.commit();
        sqlSession.close();
    }
    @org.junit.Test
    public void getUserByName(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String ,String> map = new HashMap<>();
        map.put("username","qw");
        List<User> userByName = mapper.getUserByName(map);
        userByName.forEach(x->{
            System.out.println(x);
        });
    }

}
