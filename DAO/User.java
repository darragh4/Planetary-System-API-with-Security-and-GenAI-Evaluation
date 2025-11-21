package ie.spring.moon.daos;

import ie.spring.moon.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    int deleteById(int id);
    int count();
    boolean existsByUsername(String username);
}
