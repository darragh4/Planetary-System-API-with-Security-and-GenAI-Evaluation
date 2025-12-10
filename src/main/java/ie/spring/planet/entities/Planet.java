package ie.spring.planet.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planet_Id;

    private String name;
    private String type;
    private float radius_Km;
    private float mass_Kg;
    private int orbital_Period_Days;
}
