import com.yeep.pojo.User;
import com.yeep.mapper.UserDaoIml;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
public class MyTest {
    @Test
    public void getAllUsers() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDaoIml userDaoIml1 = applicationContext.getBean("userDao",UserDaoIml.class);
        List<User> allUsers = userDaoIml1.getAllUsers();
        allUsers.forEach(x->{
            System.out.println(x);
        });
    }
    @Test
    public void deleteUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
        UserDaoIml userDaoIml1 = applicationContext.getBean("userDao",UserDaoIml.class);
        userDaoIml1.deleteUser(2);
    }

}
