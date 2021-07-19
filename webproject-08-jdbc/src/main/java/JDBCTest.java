import org.junit.Test;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String uri = "jdbc:mysql://localhost:3306/henualbum?userUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "yeep2020";
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库
        Connection connection = DriverManager.getConnection(uri, username, password);
        //3.1预编译sql
        String sql = "select * from albums";
        //3.2
        String sqlinsert = "insert albums(id, title, createtime) VALUES (?,?,?,?) ";
        //PreparedStatement preparedStatement = connection.prepareStatement(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlinsert);
        //4.1
        preparedStatement.setInt(1,1);
        preparedStatement.setString(2,"我的相册");
        preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));

        //4.2执行sql语句,查询使用executeQuery，其他都用executeUpdate()
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getObject("id"));
            System.out.println(resultSet.getObject("title"));
        }
        //5.关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

    public void test() throws ClassNotFoundException, SQLException {
        String uri = "jdbc:mysql://localhost:3306/henualbum?userUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "yeep2020";
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库
        Connection connection = DriverManager.getConnection(uri, username, password);
        //3.开启事务
        try {
            connection.setAutoCommit(false);
            String sql = "update albums set title='123456' where id=1";
            connection.prepareStatement(sql).executeUpdate();
            int i = 1/0;
            String sqlq = "update albums set title='12345' where id=1";
            connection.prepareStatement(sqlq).executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            connection.rollback();
        }finally {
            connection.close();
        }



    }

}
