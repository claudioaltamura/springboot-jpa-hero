package de.claudioaltamura.springboot.jpa.hero.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "cities", indexes = {
		@Index(name = "IDX_CITY_name", columnList = "name")
})
public class City {

    @Column(nullable = false, updatable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
    private long id;

    @NotNull
	private String name;

	public City() {}

	public City(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return String.format(
                "City[id=%d, name='%s']",
                id, name);
    }
}
