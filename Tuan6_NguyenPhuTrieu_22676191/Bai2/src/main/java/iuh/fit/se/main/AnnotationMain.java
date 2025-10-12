package iuh.fit.se.main;

import iuh.fit.se.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext("iuh.fit.se.model");

        Employee emp = context.getBean(Employee.class);
        emp.showInfo();
    }
}
