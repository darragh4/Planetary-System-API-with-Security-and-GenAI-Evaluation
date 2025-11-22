package ie.spring.moon.service;

import ie.spring.moon.dtos.CreateMoonDTO;
import ie.spring.moon.dtos.MoonDTO;

import java.util.List;

public interface MoonService {

    MoonDTO createMoon(CreateMoonDTO dto);

    List<MoonDTO> findAll();

    MoonDTO findById(int id);

    void deleteById(int id);

    List<MoonDTO> findByPlanetId(int planetId);

    int countByPlanetId(int planetId);
}
