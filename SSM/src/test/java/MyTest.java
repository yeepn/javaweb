import com.yeep.pojo.User;
import com.yeep.service.UserService;
import com.yeep.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserServiceImpl userServiceImpl = classPathXmlApplicationContext.getBean("userServiceImpl", UserServiceImpl.class);
        List<User> allUsers = userServiceImpl.getAllUsers();
        allUsers.forEach(x->{
            System.out.println(x);
        });
    }
}
