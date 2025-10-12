package iuh.fit.se;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import iuh.fit.se.beans.User;
import iuh.fit.se.beans.Group;
import iuh.fit.se.services.UserServices;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(UserServices.class);

        User user = context.getBean(User.class);
        System.out.println(user);

        Group group = context.getBean(Group.class);
        System.out.println(group);
    }
}
