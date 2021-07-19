package com.yeep.mapper;

import com.yeep.pojo.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface UserDao {
    @Select("SELECT * FROM users")
    List<User> getAllUser();

    @Select("SELECT * FROM users WHERE id=#{id}")
    User getUserById(int id);

    @Delete("DELETE FROM users WHERE id=#{id}")
    int deleteUserById(int id);

    @Insert("INSERT INTO users(username,pwd) values (#{user.username},#{user.pwd})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    int insertUser(@Param("user")User user);

    @Update("UPDATE users SET username=#{user.username},pwd=#{user.pwd} WHERE id=#{user.id}")
    int updateUser(@Param("user")User user);
}
