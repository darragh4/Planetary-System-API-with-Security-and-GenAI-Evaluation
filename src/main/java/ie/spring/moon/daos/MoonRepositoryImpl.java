package ie.spring.moon.daos;

import ie.spring.moon.entities.Moon;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MoonRepositoryImpl implements ie.spring.moon.daos.MoonRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Moon save(Moon moon) {
        String sql =
                "INSERT INTO moons (name, diameter_km, orbital_period_days, planet_id) " +
                        "VALUES (:name, :diameter_km, :orbital_period_days, :planet_id)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("name", moon.getName())
                .param("diameter_km", moon.getDiameter_Km())
                .param("orbital_period_days", moon.getOrbital_Period_Days())
                .param("planet_id", moon.getPlanet_id())
                .update(keyHolder);

        if (keyHolder.getKey() != null) {
            moon.setMoon_Id(keyHolder.getKey().intValue());
        }

        return moon;
    }

    @Override
    public Optional<Moon> findById(int id) {
        String sql = "SELECT * FROM moons WHERE moon_id = :id";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Moon.class)
                .optional();
    }

    @Override
    public List<Moon> findAll() {
        String sql = "SELECT * FROM moons";

        return jdbcClient.sql(sql)
                .query(Moon.class)
                .list();
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM moons WHERE moon_id = ?";

        return jdbcClient.sql(sql)
                .param(id)
                .update();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM moons";

        return jdbcClient.sql(sql)
                .query(Integer.class)
                .single();
    }

    @Override
    public List<Moon> findByPlanetId(int planetId) {
        String sql = "SELECT * FROM moons WHERE planet_id = :planet_id";

        return jdbcClient.sql(sql)
                .param("planet_id", planetId)
                .query(Moon.class)
                .list();
    }

    @Override
    public int countByPlanetId(int planetId) {
        String sql = "SELECT COUNT(*) FROM moons WHERE planet_id = :planet_id";

        return jdbcClient.sql(sql)
                .param("planet_id", planetId)
                .query(Integer.class)
                .single();
    }

    @Override
    public List<Moon> findByPlanetName(String planetName) {
        String sql = """
            SELECT m.*
            FROM moons m
            JOIN planets p ON m.planet_id = p.planet_id
            WHERE p.name = :planet_name
            """;

        return jdbcClient.sql(sql)
                .param("planet_name", planetName)
                .query(Moon.class)
                .list();
    }

    @Override
    public int countByPlanetName(String planetName) {
        String sql = """
            SELECT COUNT(*)
            FROM moons m
            JOIN planets p ON m.planet_id = p.planet_id
            WHERE p.name = :planet_name
        """;

        return jdbcClient.sql(sql)
                .param("planet_name", planetName)
                .query(Integer.class)
                .single();
    }
}
