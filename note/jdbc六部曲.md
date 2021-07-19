1.导入数据库驱动包

```maven
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.35</version>
 </dependency>
```

2.加载驱动

```java
String uri = "jdbc:mysql://localhost:3306/henualbum?userUnicode=true&characterEncoding=utf-8";
String username = "root";
String password = "yeep2020";
Class.forName("com.mysql.jdbc.Driver");
```

3.连接数据库

```java
Connection connection = DriverManager.getConnection(uri, username, password);
```

4.预编译sql

```java
String sqlquery = "select * from albums";//查询的sql
String sqlinsert = "insert albums(id, title, createtime) VALUES (?,?,?,?) ";
PreparedStatement preparedStatement = connection.prepareStatement(sqlinsert);
```

5.给编译的sql语句赋值

```java
preparedStatement.setInt(1,1);
preparedStatement.setString(2,"我的相册");
preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
```

6.执行sql

```java
ResultSet resultSet = preparedStatement.executeQuery();
int resultSet = preparedStatement.executeUpdate();
//ResultSet resultSet = preparedStatement.executeQuery();
```

7.查询的时候遍历结果集

```java
while (resultSet.next()){
	System.out.println(resultSet.getObject("id"));
 	System.out.println(resultSet.getObject("title"));
}
```

8.关闭连接(先开的后关)

```java
//5.关闭连接
resultSet.close();
preparedStatement.close();
 connection.close();
```

