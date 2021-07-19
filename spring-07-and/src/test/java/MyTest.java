import com.yeep.mapper.UserDaoImpl;
import com.yeep.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void getAllUsers(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        List<User> allUser = userDao.getAllUser();
        allUser.forEach(x->{
                    System.out.println(x);
                }
        );
    }
    @Test
    public  void getUserById(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        //int i = userDao.insertUser(new User(2, "yeep", "yeep2020"));
        System.out.println(userDao.getUserById(3));
    }
    @Test
    public void insertUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        int i = userDao.insertUser(new User(2, "yeep", "yeep2020"));
        System.out.println(i);
    }
    @Test
    public void updateUser(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans-context.xml");
        UserDaoImpl userDao = applicationContext.getBean("userDao", UserDaoImpl.class);
        int i = userDao.updateUser(new User(2, "yeep1", "yeep2020"));
        System.out.println(i);
    }

}
