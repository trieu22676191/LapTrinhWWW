package iuh.fit.se.main;

import iuh.fit.se.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        Employee emp1 = (Employee) context.getBean("employeeBean");
        emp1.showInfo();

        Employee emp2 = (Employee) context.getBean("employeeConstructor");
        emp2.showInfo();
    }
}
