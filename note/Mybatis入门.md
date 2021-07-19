# Mybatis入门

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

## 使用流程

整个过程只需要配置为UserDao添加方法，然后去MyMapper的配置文件对应着用sql去实现就可以了

### 1.导入maven依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>MyBatis-02-test</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.32</version>
        </dependency>
    </dependencies>
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
</project>
```

### 2.mybatis核心依赖配置：

需要注意的地方是配置jdbc路径到表级别 登录用户名 密码

```
<!--mybatis-config.xml-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 环境，可以配置多个，default：指定采用哪个环境 -->
    <environments default="test">
        <!-- id：唯一标识 -->
        <environment id="test">
            <!-- 事务管理器，JDBC类型的事务管理器 -->
            <transactionManager type="JDBC" />
            <!-- 数据源，池类型的数据源 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/ssmdemo?userUnicode=true&amp;characterEncoding=utf-8" />
                <property name="username" value="root" />
                <property name="password" value="yeep2020" />
            </dataSource>
        </environment>
    </environments>
    <!--下面用于注册自己写的mapper文件-->
    <mappers>
        <mapper resource="Mappers/MyMapper.xml" />
    </mappers>
</configuration>
```

### 3.创建pojo包存放用到的普通java类

属性必须私有并且提供getter、setter函数

```java

```

### 4.创建UserDao接口用于约定增删改查操作

### 5.创建MyMapper.xml

这里的nameuser要和你写的UserDao操作接口相对应并且是全路径，各个标签对应id就是对应函数的函数名字，resultType就是对应表字段的类的全路径，注意

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.mybatis.example.BlogMapper">
  <select id="selectBlog" resultType="Blog">
    select * from Blog where id = #{id}
  </select>
</mapper>
```

### 6.编写静态MyBatis类用于获取SqlSession：

```java
package com.yeep.util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
```

### 7.后面的步骤：就是在接口中定义方法，然后去自定义的map中实现

值得注意的就是增删改是需要commit才会生效的

sqlsession他不是线程安全的，所以不应该被定义成全局变量，应当在每个方法中分别获取他

### 8.模糊查询：利用map

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yeep.pojo.mapper.UserDao">
    <select id="getAllUser" resultType="com.yeep.pojo.User">
        select * from users
    </select>
    <insert id="insertUser" parameterType="com.yeep.pojo.User">
        insert into users(id,username,pwd) values (#{id},#{username},#{pwd})
    </insert>
    <delete id="deleteUser" parameterType="int">
        delete from users where id=#{id}
    </delete>
    <update id="updateUser" parameterType="com.yeep.pojo.User">
        update users
        set username = #{username},pwd=#{pwd}
        where id=#{id};
    </update>
    <select id="getUserByName" parameterType="map" resultType="com.yeep.pojo.User">
        select * from users where username=#{username}
    </select>
</mapper>
```

