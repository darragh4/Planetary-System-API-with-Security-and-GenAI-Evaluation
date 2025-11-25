package ie.spring.moon.exceptions;

import lombok.Getter;

@Getter
public class MoonNotFoundException extends RuntimeException{
    private final int id;

    public MoonNotFoundException(int id) {
        super("Moon with id " + id + " not found");
        this.id = id;
    }
}
