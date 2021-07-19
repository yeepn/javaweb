package com.yeep.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    //当字段不一致的时候在写sql语句的时候可以使用as字句来给数据库字段起别名
    private int userid;
    private String username;
    private  String pwd;
}
