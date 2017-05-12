package jdbc;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc/spring-jdbc.xml");
        HibernateDao hd = (HibernateDao) context.getBean("hibernateDao");
        List<Employee> list = hd.getSingleValue(1);
        for (Employee s : list) {
            System.out.println(s.getId() + " " + s.getFullname() + " " + s.getEmail() + " " + s.getAddress());
        }
    }
}
