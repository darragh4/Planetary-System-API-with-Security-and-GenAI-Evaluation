package ie.spring.planet.daos;

import ie.spring.planet.entities.Planet;
import ie.spring.planet.dtos.NameMassDTO;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository {

    Planet save(Planet planet);
    Optional<Planet> findById(int id);
    List<Planet> findAll();
    List<Planet> findByType(String type);
    int updateMass(int id, float newMass);
    int deleteById(int id);
    int count();
    List<NameMassDTO> findNameAndMassOnly();
}
