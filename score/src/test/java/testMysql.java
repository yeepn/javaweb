import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testMysql {
    public static void main(String[] args) {
        try {


            String Url = "jdbc:mysql://159.75.70.2/scoreuser";//参数参考MySql连接数据库常用参数及代码示例
            String name = "ScoreUser";//数据库用户名
            String psd = "XyESzxx63BwchZwX";//数据库密码
            String jdbcName = "com.mysql.jdbc.Driver";
            String sql = "insert into user values(?,?)";
            try {
                Class.forName(jdbcName);
                Connection con = null;
                try {
                    con = DriverManager.getConnection(Url, name, psd);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                PreparedStatement pst = null;
                try {
                    pst = con.prepareStatement(sql);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    pst.setString(1, "sno");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    pst.setString(2, "pwd");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    pst.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
