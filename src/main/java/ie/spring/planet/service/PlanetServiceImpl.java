package ie.spring.planet.service;

import ie.spring.planet.daos.PlanetRepository;
import ie.spring.planet.dtos.CreatePlanetDTO;
import ie.spring.planet.dtos.NameMassDTO;
import ie.spring.planet.dtos.PlanetDTO;
import ie.spring.planet.entities.Planet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;

    @Override
    @Transactional
    public PlanetDTO createPlanet(CreatePlanetDTO dto) {
        Planet planet = new Planet();
        planet.setName(dto.getName());
        planet.setType(dto.getType());
        planet.setRadius_Km(dto.getRadiusKm());
        planet.setMass_Kg(dto.getMassKg());
        planet.setOrbital_Period_Days(dto.getOrbitalPeriodDays());

        Planet saved = planetRepository.save(planet);
        return toDto(saved);
    }

    @Override
    public List<PlanetDTO> findAll() {
        return planetRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public PlanetDTO findById(int id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Planet with id " + id + " not found"));
        return toDto(planet);
    }

    @Override
    @Transactional
    public void updateMass(int id, float newMass) {
        int rows = planetRepository.updateMass(id, newMass);
        if (rows == 0) {
            throw new RuntimeException("Planet with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int rows = planetRepository.deleteById(id);
        if (rows == 0) {
            throw new RuntimeException("Planet with id " + id + " not found");
        }
    }

    @Override
    public List<PlanetDTO> findByType(String type) {
        return planetRepository.findByType(type)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<NameMassDTO> getNameAndMassOnly() {
        return planetRepository.findNameAndMassOnly();
    }

    // mapping helper
    private PlanetDTO toDto(Planet planet) {
        return new PlanetDTO(
                planet.getPlanet_Id(),
                planet.getName(),
                planet.getType(),
                planet.getRadius_Km(),
                planet.getMass_Kg(),
                planet.getOrbital_Period_Days()
        );
    }
}
