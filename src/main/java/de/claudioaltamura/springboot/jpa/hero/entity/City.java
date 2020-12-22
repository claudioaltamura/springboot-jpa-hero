package de.claudioaltamura.springboot.jpa.hero.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
                "City[id=%d, name='%s']",
                id, name);
    }
}
