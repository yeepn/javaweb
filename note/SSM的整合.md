# 1.导入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>SSM-01</artifactId>
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
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.49</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
    </dependency>
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.2</version>
    </dependency>
</dependencies>

</project>
```

# 2.创建相关的包

![image-20210718192302886](C:\Users\yeyeyeping\AppData\Roaming\Typora\typora-user-images\image-20210718192302886.png)

# 3.mybatis层编写

## 1.创建mybatis的核心配置文件:mybatis-config.xml:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.yeep.pojo"/>
    </typeAliases>
        <mappers>
        <mapper resource="com/yeep/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```



## 2.准备数据库连接的文件:database.properties:

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.username=root
jdbc.password=yeep2020
jdbc.url=mysql:jdbc://localhost:3306/ssmdemo?userSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/ShangHai
```

## 3.创建实体类：

4个注解

```java
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

```

## 4.约定UserMapper接口

```java
package com.yeep.dao;
import com.yeep.pojo.User;
import java.util.List;
public interface UserMapper {
    List<User> getAllUsers();
    User getUser(int id);
    int deleteUser(int id);
    int updateUser(User user);
    int insertUser(User user);
}
```

## 5.编写UserMapper接口对应的实现使用xml的方式，注意这个文件和核心配置文件的区别，其实就是改了两处

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.yeep.dao.UserMapper">
    <select id="getAllUsers" resultType="user">
        select * from users
    </select>
<select id="getUserByid" parameterType="integer" resultType="user">
    select * from users where id=#{userid}
</select>
    <delete id="deleteUser" parameterType="user">
        delete from users where id=#{userid}
    </delete>
    <update id="updateUser" parameterType="user">
        update users
        set username = #{username},pwd=#{pwd} 
        where id=#{userid};
    </update>
    <insert id="insertUser" parameterType="user">
        insert into users(username,pwd) values #{username},#{pwd}
    </insert>
</mapper>
```

## 6.注册mapper到mybatis-config.xml核心配置文件

这里注意注册有俩种方式就是可以通过resource注册，当名字xml和接口名字一样的时候通过类名注册,推荐用resource路径的方式注册

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.yeep.pojo"/>
    </typeAliases>
    <!--全路径-->
    <mappers>
        <mapper class="com.yeep.dao.UserMapper"/>
    </mappers>
</configuration>
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.yeep.pojo"/>
    </typeAliases>
    <mappers>
        <mapper resource="/com/yeep/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```



## 7.编写service接口和service实现类

```java
package com.yeep.service;

import com.yeep.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByid(int id);
    int deleteUser(int id);
    int updateUser(User user);
    int insertUser(User user);
}
```

```java
package com.yeep.service;

import com.yeep.dao.UserMapper;
import com.yeep.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserMapper userMapper;
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserByid(int id) {
        return userMapper.getUserByid(id);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
```

基本操作全部完成

# 4.整合Spring和MyBatis

## 1.创建spring-dao.xml文件

配置文件主要干的事：获取数据源、配置连接池、注册sqlsessionFactoryBean、注册册动态代理类MapperScannerConfigurer（以动态代理的形式去实现了sqlsession的调用，配置了这个以后不用管sqlsessionTemplate的事情了）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder location="classpath:database.properties"/>

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>


        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <property name="acquireRetryAttempts" value="3"/>
        <property name="checkoutTimeout" value="10000"/>
        <property name="autoCommitOnClose" value="false"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" >
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.yeep.dao"></property>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

    

</beans>
```

# 5.整合spring与service层

创建spring-service.xml配置文件，主要干的事就是配置事务管理器,以及配置一些注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder location="classpath:database.properties"/>
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="dataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```



# 6.整合spring与springmvc层

## 1.为模块添加web支持

## 2.配置web.xml：

### 添加dispatchservlet，配置该servlet的参数contextConfigLocation为applicationcontext-beans.xml,path为/

### 配置spring自带的CharacterEncodingFilter，解决乱码问题，patten：/*

### 设置session的生命周期

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationcontext-bean.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>springmvc</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>springmvc</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

## 3.创建spring-servlet.xml配置文件

### 配置注解驱动

### 配置默认的handler

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/context
   http://www.springframework.org/schema/context/spring-context.xsd
   http://www.springframework.org/schema/mvc
   https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <mvc:annotation-driven/>
    <mvc:default-servlet-handler/>
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

# 7.applicacontext-beans.xml整合

## 在applicationcontext-beans.xml中引入三层的sping整合配置文件，配置context扫描器，启用注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <import resource="classpath:spring-dao.xml"/>
    <import resource="classpath:spring-service.xml"/>
    <import resource="classpath:spring-servlet.xml"/>
    <context:annotation-config></context:annotation-config>
    <context:component-scan base-package="com.yeep.service"/>
    <context:component-scan base-package="com.yeep.controller"/>
</beans>
```

# 8.编写controller代码

# 9.在artifacts里面创建/WEB-INF/lib目录并导入全部依赖
