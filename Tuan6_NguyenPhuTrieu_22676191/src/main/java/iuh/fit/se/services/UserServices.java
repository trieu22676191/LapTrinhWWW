package iuh.fit.se.services;

import iuh.fit.se.beans.Group;
import iuh.fit.se.beans.User;
import org.springframework.context.annotation.Bean;

public class UserServices {
    @Bean
    public Group groupService() {
        return new Group(1, "Admin Group");
    }

    @Bean
    public User userService() {
        return new User(1, "User 01", "123456", groupService());
    }
}
