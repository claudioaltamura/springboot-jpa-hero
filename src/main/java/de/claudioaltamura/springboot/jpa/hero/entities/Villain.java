package de.claudioaltamura.springboot.jpa.hero.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "villains")
public class Villain {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "antagonists")
  private Set<Hero> heroes = new HashSet<>();

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "city_id")
  private City city;

  public Villain(String name, City city) {
    this.name = name;
    this.city = city;
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

  public Set<Hero> getHeroes() {
    return heroes;
  }

  public void setHeroes(Set<Hero> heroes) {
    this.heroes = heroes;
  }

  public void setCity(City city) { this.city = city; }

  public City getCity() { return city; }

  @Override
  public String toString() {
    return String.format(
            "Villain[id=%d, name='%s', city='%s']",
            id, name, city);
  }}
