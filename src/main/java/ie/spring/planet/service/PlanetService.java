package ie.spring.planet.service;

import ie.spring.planet.dtos.CreatePlanetDTO;
import ie.spring.planet.dtos.NameMassDTO;
import ie.spring.planet.dtos.PlanetDTO;

import java.util.List;

public interface PlanetService {

    PlanetDTO createPlanet(CreatePlanetDTO dto);

    List<PlanetDTO> findAll();

    PlanetDTO findById(int id);

    void updateMass(int id, float newMass);

    void deleteById(int id);

    List<PlanetDTO> findByType(String type);

    List<NameMassDTO> getNameAndMassOnly();
}
