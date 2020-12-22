package de.claudioaltamura.springboot.jpa.hero.service;

public class EntityNotFoundException extends RuntimeException {

    private final long id;

    public EntityNotFoundException(long id) {
        super("entity not found '" + id + "'.");
        this.id = id;
    }


}
