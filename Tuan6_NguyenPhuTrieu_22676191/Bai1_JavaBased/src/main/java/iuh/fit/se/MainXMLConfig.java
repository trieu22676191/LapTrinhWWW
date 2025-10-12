package iuh.fit.se;

import iuh.fit.se.beans.Student;
import iuh.fit.se.model.Class_;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXMLConfig {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Student student = context.getBean("student1",Student.class);
    Class_ class_1 = context.getBean("class1",Class_.class);
    Class_ class_2 = context.getBean("class2",Class_.class);
    public static void main(String[] args) {
        MainXMLConfig main = new MainXMLConfig();
        System.out.println(main.student);
        System.out.println(main.class_1);
        System.out.println(main.class_2);
    }
}
