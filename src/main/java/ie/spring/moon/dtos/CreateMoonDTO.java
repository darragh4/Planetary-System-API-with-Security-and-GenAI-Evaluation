package ie.spring.moon.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMoonDTO {
    private String name;
    private float diameterKm;
    private int orbitalPeriodDays;
    private int planetId;
}
