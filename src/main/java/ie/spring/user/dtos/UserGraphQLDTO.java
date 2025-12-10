package ie.spring.user.dtos;

public record UserGraphQLDTO(
    int id,
    String username,
    String role
){}
