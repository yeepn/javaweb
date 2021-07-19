import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationbeans.xml");
        User useri = (User) applicationContext.getBean("useri");
        System.out.println(useri);
    }
}
