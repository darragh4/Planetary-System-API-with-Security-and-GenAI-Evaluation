package ie.spring.config;

import ie.spring.user.daos.UserRepository;
import ie.spring.user.entities.Role;
import ie.spring.user.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Only insert users if the table is empty
            if (!userRepository.existsByUsername("admin")) {
                userRepository.save(new User(
                        0,
                        "admin",
                        encoder.encode("admin123"),
                        Role.ADMIN
                ));
            }

            if (!userRepository.existsByUsername("staff")) {
                userRepository.save(new User(
                        0,
                        "staff",
                        encoder.encode("staff123"),
                        Role.STAFF
                ));
            }

            if (!userRepository.existsByUsername("student")) {
                userRepository.save(new User(
                        0,
                        "student",
                        encoder.encode("student123"),
                        Role.STUDENT
                ));
            }
        };
    }
}
