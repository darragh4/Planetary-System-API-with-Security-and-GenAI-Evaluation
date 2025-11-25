package ie.spring.planet.exceptions;

import lombok.Getter;

@Getter
public class PlanetNotFoundException extends RuntimeException{

    private final int id;

    public PlanetNotFoundException(int id) {
        super("Planet id " + id + " not found");
        this.id = id;
    }
}
