package ie.spring.planet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreatePlanetDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Radius is required")
    @Positive(message = "Radius must be positive")
    private float radiusKm;

    @NotNull(message = "Mass is required")
    @Positive(message = "Mass must be positive")
    private float massKg;

    @NotNull(message = "Orbital Period is required")
    @Positive(message = "Orbital Period must be positive")
    private int orbitalPeriodDays;
}
