package com.yeep.mapper;

import com.yeep.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
public interface UserDao {
    @Select("Select * from users")
    List<User> getAllUsers();
    @Select("Select * from users where id=#{id}")
    User getUser(@Param("id") int id);
    @Select("Delete from users where id=#{id}")
    void deleteUser(@Param("id")int id);
    @Insert("Insert into users(id,username,pwd) values(#{id},#{username},#{pwd})")
    int insertUser(@Param("user") User user);
}
