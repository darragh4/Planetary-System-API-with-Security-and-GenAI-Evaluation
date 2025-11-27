package ie.spring.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserInput(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Role is required (ADMIN, STAFF, STUDENT)")
        String role
) {}
