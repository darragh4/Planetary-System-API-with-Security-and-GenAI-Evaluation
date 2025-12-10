package ie.spring.planet.controllers;

import ie.spring.planet.dtos.CreatePlanetDTO;
import ie.spring.planet.dtos.NameMassDTO;
import ie.spring.planet.dtos.PlanetDTO;
import ie.spring.planet.dtos.UpdatePlanetMassDTO;
import ie.spring.planet.service.PlanetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
@AllArgsConstructor
public class PlanetController {

    private final PlanetService planetService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanetDTO createPlanet(@Valid @RequestBody CreatePlanetDTO dto) {
        return planetService.createPlanet(dto);
    }


    @GetMapping
    public List<PlanetDTO> getAllPlanets() {
        return planetService.findAll();
    }


    @GetMapping("/{id}")
    public PlanetDTO getPlanetById(@PathVariable int id) {
        return planetService.findById(id);
    }


    @PutMapping("/{id}/mass")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePlanetMass(@PathVariable int id,
                                 @Valid @RequestBody UpdatePlanetMassDTO dto) {
        planetService.updateMass(id, dto.getMassKg());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlanet(@PathVariable int id) {
        planetService.deleteById(id);
    }

    @GetMapping("/type/{type}")
    public List<PlanetDTO> getPlanetsByType(@PathVariable String type) {
        return planetService.findByType(type);
    }

    @GetMapping("/summary")
    public List<NameMassDTO> getPlanetNameAndMassOnly() {
        return planetService.getNameAndMassOnly();
    }
}
