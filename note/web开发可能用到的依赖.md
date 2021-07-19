## 发行版不可用怎么办：

#### 可能有问题的地方：

![image-20210714213552734](C:\Users\yeyeyeping\AppData\Roaming\Typora\typora-user-images\image-20210714213552734.png)

![image-20210714213605711](C:\Users\yeyeyeping\AppData\Roaming\Typora\typora-user-images\image-20210714213605711.png)

#### 利用maven的标签指定编译后的版本永久解决这些问题：

```xml
<properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
 </properties>
```



## jdbc相关：

### jdbc的mysqlconnect依赖

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.32</version>
</dependency>
```

### jdbc url格式

```java
String uri = "jdbc:mysql://localhost:3306/ssmdemo?userUnicode=true&characterEncoding=utf-8"
```

### jdbc加载驱动

```java
Class.forName("com.mysql.jdbc.Driver");
```

### junit单元测试框架依赖：

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
```

## javaweb相关

### jsp、servlet依赖：

```xml
<dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
</dependency>

<dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2</version>
      <scope>provided</scope>
</dependency>
```

### el表达式的依赖和jsp标签的依赖：

```
<dependency>
  <groupId>org.apache.taglibs</groupId>
  <artifactId>taglibs-standard-impl</artifactId>
  <version>1.2.5</version>
</dependency>
```

### jsp头部导入的taglib、保证el表达式可用

```jsp
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--使用el表达式--%>
<%@ page isELIgnored="false"%>
```

## mybatis

### mybatis依赖

```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.6</version>
</dependency>
```

### mybatis核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

## Maven

#### Maven资源导出问题解决方案：

```xml
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
```

#### 