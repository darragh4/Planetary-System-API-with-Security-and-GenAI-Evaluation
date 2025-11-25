package ie.spring.user.daos;

import ie.spring.user.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcClient jdbcClient;

    @Override
    public User save(User user) {
        String sql =
                "INSERT INTO users (username, password, role) " +
                        "VALUES (:username, :password, :role)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("role", user.getRole().ordinal())
                .update(keyHolder);

        if (keyHolder.getKey() != null) {
            user.setUserId(keyHolder.getKey().intValue());
        }

        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = :id";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(User.class)
                .optional();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = :username";

        return jdbcClient.sql(sql)
                .param("username", username)
                .query(User.class)
                .optional();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";

        return jdbcClient.sql(sql)
                .query(User.class)
                .list();
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        return jdbcClient.sql(sql)
                .param(id)
                .update();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM users";

        return jdbcClient.sql(sql)
                .query(Integer.class)
                .single();
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = :username";

        int count = jdbcClient.sql(sql)
                .param("username", username)
                .query(Integer.class)
                .single();

        return count > 0;
    }
}
