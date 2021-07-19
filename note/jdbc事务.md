### 1.Junit单元测试工具

通过在方法上加注解来测试方法

@Test加了这个注解的方法可以直接运行测试，也可以同时测试多个方法

```java
try {
    //开启事务
	connection.setAutoCommit(false);
	String sql = "update albums set title='123456' where 		id=1";     		connection.prepareStatement(sql).executeUpdate();
	int i = 1/0;//制造异常
	String sqlq = "update albums set title='12345' where id=1";           							
	connection.commit();//运行正常提交
    connection.prepareStatement(sqlq).executeUpdate();
} catch (SQLException throwables) {
	connection.rollback();//出现异常就回滚
	connection.close();
}
```

