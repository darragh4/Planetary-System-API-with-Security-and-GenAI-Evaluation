package ie.spring.planet.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdatePlanetMassDTO {

    @Positive
    private float massKg;
}
