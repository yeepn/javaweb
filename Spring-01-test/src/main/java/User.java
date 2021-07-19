import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class User {
    private int Id;
    private String username;
    private List<String> hobbies;
    private String[] strenths;
    private Properties p;

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", hobbies=" + hobbies +
                ", strenths=" + Arrays.toString(strenths) +
                ", p=" + p +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public void setStrenths(String[] strenths) {
        this.strenths = strenths;
    }

    public void setP(Properties p) {
        this.p = p;
    }

    public User(int id, String username, String pwd) {
        Id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public User() {
    }

    private String pwd;

    public void setId(int id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public int getId() {
        return Id;
    }
}
