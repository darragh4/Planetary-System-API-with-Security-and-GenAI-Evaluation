package ie.spring.moon.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMoonDTO {

    @NotBlank(message = "Moon name is required")
    @Size(max = 50, message = "Moon name must be at most 50 characters")
    private String name;

    @NotNull(message = "Diameter is required")
    @Positive(message = "Diameter must be positive")
    private float diameterKm;

    @NotNull(message = "Orbital Period is required")
    @Positive(message = "Orbital Period must be positive")
    private int orbitalPeriodDays;

    @NotNull(message = "Planet id is required")
    private int planetId;
}
