package iuh.fit.se.config;

import iuh.fit.se.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Student student() {
        return new Student("101", "Nguyen Van B");
    }
}