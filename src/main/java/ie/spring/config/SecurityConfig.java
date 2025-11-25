package ie.spring.config;

import ie.spring.user.daos.UserRepository;
import ie.spring.user.entities.User;
import ie.spring.user.entities.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // allow swagger + h2 without login
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()

                        // Everyone with any role can READ planets/moons
                        .requestMatchers(HttpMethod.GET, "/planets/**", "/moons/**")
                            .hasAnyRole("ADMIN", "STAFF", "STUDENT")

                        // Only STAFF or ADMIN can create/update
                        .requestMatchers(HttpMethod.POST, "/planets/**", "/moons/**")
                            .hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.PUT, "/planets/**", "/moons/**")
                            .hasAnyRole("ADMIN", "STAFF")

                        // Only ADMIN can delete
                        .requestMatchers(HttpMethod.DELETE, "/planets/**", "/moons/**")
                            .hasRole("ADMIN")

                        // Anything else must be authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }
}
