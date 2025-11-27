package ie.spring.user.controllers;

import ie.spring.user.daos.UserRepository;
import ie.spring.user.dtos.CreateUserInput;
import ie.spring.user.dtos.UserGraphQLDTO;
import ie.spring.user.entities.Role;
import ie.spring.user.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@Validated
@RequiredArgsConstructor
public class UserGraphQLController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN only - find user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public UserGraphQLDTO userById(@Argument int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + "not found"));

        return toDto(user);
    }

    //ADMIN only - create a new user
    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public UserGraphQLDTO createUser(@Valid @Argument CreateUserInput input) {
        Role role = Role.valueOf(input.role().toUpperCase());

        User user = new User();
        user.setUsername(input.username());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(role);

        User saved = userRepository.save(user);

        return toDto(saved);
    }

    private UserGraphQLDTO toDto(User user) {
        return new UserGraphQLDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole().name()
        );
    }
}
