package ie.spring.planet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameMassDTO {
    private String name;
    private float mass_Kg;
}
