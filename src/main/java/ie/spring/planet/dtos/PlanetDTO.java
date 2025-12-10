package ie.spring.planet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDTO {

    private int planetId;
    private String name;
    private String type;
    private float radiusKm;
    private float massKg;
    private int orbitalPeriodDays;
}
