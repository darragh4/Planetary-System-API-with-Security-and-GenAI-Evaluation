package ie.spring.moon.service;

import ie.spring.moon.daos.MoonRepository;
import ie.spring.moon.dtos.CreateMoonDTO;
import ie.spring.moon.dtos.MoonDTO;
import ie.spring.moon.entities.Moon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MoonServiceImpl implements MoonService {

    private final MoonRepository moonRepository;

    @Override
    @Transactional
    public MoonDTO createMoon(CreateMoonDTO dto) {
        Moon moon = new Moon();
        moon.setName(dto.getName());
        moon.setDiameter_Km(dto.getDiameterKm());
        moon.setOrbital_Period_Days(dto.getOrbitalPeriodDays());
        moon.setPlanet_id(dto.getPlanetId());

        Moon saved = moonRepository.save(moon);
        return toDto(saved);
    }

    @Override
    public List<MoonDTO> findAll() {
        return moonRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public MoonDTO findById(int id) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Moon with id " + id + " not found"));
        return toDto(moon);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int rows = moonRepository.deleteById(id);
        if (rows == 0) {
            throw new RuntimeException("Moon with id " + id + " not found");
        }
    }

    @Override
    public List<MoonDTO> findByPlanetId(int planetId) {
        return moonRepository.findByPlanetId(planetId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public int countByPlanetId(int planetId) {
        return moonRepository.countByPlanetId(planetId);
    }

    private MoonDTO toDto(Moon moon) {
        return new MoonDTO(
                moon.getMoon_Id(),
                moon.getName(),
                moon.getDiameter_Km(),
                moon.getOrbital_Period_Days(),
                moon.getPlanet_id()
        );
    }

    @Override
    public List<MoonDTO> findByPlanetName(String planetName) {
        return moonRepository.findByPlanetName(planetName)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public int countByPlanetName(String planetName) {
        return moonRepository.countByPlanetName(planetName);
    }
}
