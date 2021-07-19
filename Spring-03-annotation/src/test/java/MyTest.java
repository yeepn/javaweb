import com.yeep.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationbeans.xml");
        People people = applicationContext.getBean("people", People.class);
       // people.getCat().shout();
        people.getDog().shout();
    }
}
