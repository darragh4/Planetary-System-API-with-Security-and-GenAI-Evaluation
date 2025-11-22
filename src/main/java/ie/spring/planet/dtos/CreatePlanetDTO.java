package ie.spring.planet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreatePlanetDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @Positive
    private float radiusKm;

    @Positive
    private float massKg;

    @Positive
    private int orbitalPeriodDays;
}
