package ie.spring;

import ie.spring.planet.daos.PlanetRepository;
import ie.spring.moon.daos.MoonRepository;
import ie.spring.user.daos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLayerSmokeTest implements CommandLineRunner {

    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        System.out.println("=== Testing PlanetRepository ===");
        System.out.println("Planet count: " + planetRepository.count());
        planetRepository.findAll().forEach(System.out::println);

        System.out.println("=== Testing findById(1) ===");
        planetRepository.findById(1).ifPresentOrElse(
                p -> System.out.println("Found: " + p),
                () -> System.out.println("Planet with id 1 not found")
        );

        System.out.println("=== Testing MoonRepository ===");
        System.out.println("Moon count: " + moonRepository.count());
        moonRepository.findAll().forEach(System.out::println);

        System.out.println("=== Testing ie.spring.user.daos.UserRepository ===");
        System.out.println("User count: " + userRepository.count());
        userRepository.findAll().forEach(System.out::println);
    }
}
