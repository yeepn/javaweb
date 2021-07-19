# 1.导入依赖

maven配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>spring-07-and</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <build>
        <!--在build中配置resources，来防止我们资源导出失败的问题-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.49</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>
</dependencies>
</project>
```

# 2.新建applicationbeans-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/ssmdemo?userUnicode=true&amp;characterEncoding=utf-8"></property>
        <property name="password" value="yeep2020"></property>
        <property name="username" value="root"></property>
    </bean>
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"></property>
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
    </bean>
    <context:annotation-config/>
   
    <context:component-scan base-package="com.yeep.mapper"/>
        <bean id="usermapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
        <property name="mapperInterface" value="com.yeep.mapper.UserDao"></property>
    </bean>
</beans>
```

## 1.配置数据源 

配置bean，通过ioc容器注册数据源

```xml
 <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/ssmdemo?userUnicode=true&amp;characterEncoding=utf-8"></property>
        <property name="password" value="yeep2020"></property>
        <property name="username" value="root"></property>
    </bean>
```

## 2.根据datasouce获取sqlSessionFactoryBean

```xml
 <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"></property>
    </bean>
```

## 3.根据sqlSessionFactoryBean注册sqlsession

```xml
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
    </bean>
```

## 4.写完了DAO的接口操作不要忘记在bean中注册Mapper

```xml
<bean id="usermapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    <property name="mapperInterface" value="com.yeep.mapper.UserDao"></property>
</bean>
```

# 3.新建DAO（约定操作）接口和DAOImpl

## 1.直接在DAO接口用注解写sql，当注解无法满足需求的时候可以新建mybats-config.xml通过xml标签配置,这里需要注意的是@param注解主要还是用在多个复杂类型参数的时候，单个参数完全没必要使用，单个复杂类型的参数如果加上该注解反而会引起异常。另外插入操作时如果不希望自己补充id需要让数据库的id需要让id设置自增属性

```java
package com.yeep.mapper;

import com.yeep.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

```

## 2.将DAO接口注册到MapperFactoryBean

```xml
<bean id="usermapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    <property name="mapperInterface" value="com.yeep.mapper.UserDao"></property>
</bean>
```

## 3.编写实现类DAOImpl

```java
package com.yeep.mapper;

import com.yeep.pojo.User;
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

```

# 4.整合测试

```java
import com.yeep.mapper.UserDaoImpl;
import com.yeep.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void getAllUsers(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        List<User> allUser = userDao.getAllUser();
        allUser.forEach(x->{
                    System.out.println(x);
                }
        );
    }
    @Test
    public  void getUserById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        //int i = userDao.insertUser(new User(2, "yeep", "yeep2020"));
        System.out.println(userDao.getUserById(3));
    }
    @Test
    public void insertUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        int i = userDao.insertUser(new User(2, "yeep", "yeep2020"));
        System.out.println(i);
    }
    @Test
    public void updateUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        int i = userDao.updateUser(new User(2, "yeep1", "yeep2020"));
        System.out.println(i);
    }

}
```

