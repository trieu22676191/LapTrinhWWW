package iuh.fit.se;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import iuh.fit.se.beans.Student;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student s1 = context.getBean("student1", Student.class);
        System.out.println(s1);
    }
}
