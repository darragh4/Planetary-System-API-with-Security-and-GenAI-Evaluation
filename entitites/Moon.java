package ie.spring.moon.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "moons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moon_Id;

    private String name;
    private float diameter_Km;
    private int orbital_Period_Days;
    private int planet_id;
}
