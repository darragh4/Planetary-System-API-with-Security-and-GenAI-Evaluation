package ie.spring.planet.daos;

import ie.spring.planet.entities.Planet;
import ie.spring.planet.dtos.NameMassDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PlanetRepositoryImpl implements PlanetRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Planet save(Planet planet) {
        String sql =
                "INSERT INTO planets (name, type, radius_km, mass_kg, orbital_period_days) " +
                        "VALUES (:name, :type, :radius_km, :mass_kg, :orbital_period_days)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("name", planet.getName())
                .param("type", planet.getType())
                .param("radius_km", planet.getRadius_Km())
                .param("mass_kg", planet.getMass_Kg())
                .param("orbital_period_days", planet.getOrbital_Period_Days())
                .update(keyHolder);

        if (keyHolder.getKey() != null) {
            planet.setPlanet_Id(keyHolder.getKey().intValue());
        }

        return planet;
    }

    @Override
    public Optional<Planet> findById(int id) {
        String sql = "SELECT * FROM planets WHERE planet_id = :id";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Planet.class)
                .optional();
    }

    @Override
    public List<Planet> findAll() {
        String sql = "SELECT * FROM planets";

        return jdbcClient.sql(sql)
                .query(Planet.class)
                .list();
    }

    @Override
    public List<Planet> findByType(String type) {
        String sql = "SELECT * FROM planets WHERE type = :type";

        return jdbcClient.sql(sql)
                .param("type", type)
                .query(Planet.class)
                .list();
    }

    @Override
    public int updateMass(int id, float newMass) {
        String sql = "UPDATE planets SET mass_kg = ? WHERE planet_id = ?";

        return jdbcClient.sql(sql)
                .param(newMass)
                .param(id)
                .update();
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM planets WHERE planet_id = ?";

        return jdbcClient.sql(sql)
                .param(id)
                .update();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM planets";

        return jdbcClient.sql(sql)
                .query(Integer.class)
                .single();
    }

    @Override
    public List<NameMassDTO> findNameAndMassOnly() {
        String sql = "SELECT name, mass_kg FROM planets";

        return jdbcClient.sql(sql)
                .query(NameMassDTO.class)
                .list();
    }
}
