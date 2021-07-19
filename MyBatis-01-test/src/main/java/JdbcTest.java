import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class JdbcTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        //1.编写uri
        String uri = "jdbc:mysql://localhost:3306/ssmdemo?userUnicode=true&characterEncoding=utf-8";
        //2.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3.定义密码
        String password = "yeep2020";
        String user = "root";
        //4获取连接
        Connection connection = DriverManager.getConnection(uri, user, password);
        String sql = "select * from tb_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
