package ie.spring.moon.controllers;

import ie.spring.moon.dtos.CreateMoonDTO;
import ie.spring.moon.dtos.MoonDTO;
import ie.spring.moon.service.MoonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moons")
@AllArgsConstructor
public class MoonController {

    private final MoonService moonService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MoonDTO createMoon(@Valid @RequestBody CreateMoonDTO dto) {
        return moonService.createMoon(dto);
    }


    @GetMapping
    public List<MoonDTO> getAllMoons() {
        return moonService.findAll();
    }


    @GetMapping("/{id}")
    public MoonDTO getMoonById(@PathVariable int id) {
        return moonService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMoon(@PathVariable int id) {
        moonService.deleteById(id);
    }

    @GetMapping("/planet/{planetId}")
    public List<MoonDTO> getMoonsByPlanetId(@PathVariable int planetId) {
        return moonService.findByPlanetId(planetId);
    }


    @GetMapping("/planet/{planetId}/count")
    public int countMoonsByPlanetId(@PathVariable int planetId) {
        return moonService.countByPlanetId(planetId);
    }

    @GetMapping("/planet/name/{planetName}")
    public List<MoonDTO> getMoonsByPlanetName(@PathVariable String planetName) {
        return moonService.findByPlanetName(planetName);
    }

    @GetMapping("/planet/name/{planetName}/count")
    public int countMoonsByPlanetName(@PathVariable String planetName) {
        return moonService.countByPlanetName(planetName);
    }
}
