package ie.spring.moon.daos;

import ie.spring.moon.entities.Moon;

import java.util.List;
import java.util.Optional;

public interface MoonRepository {

    Moon save(Moon moon);

    Optional<Moon> findById(int id);

    List<Moon> findAll();

    int deleteById(int id);

    int count();

    List<Moon> findByPlanetId(int planetId);

    int countByPlanetId(int planetId);
}
