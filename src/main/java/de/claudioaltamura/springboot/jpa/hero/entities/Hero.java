package de.claudioaltamura.springboot.jpa.hero.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "heroes_villains",
      joinColumns = @JoinColumn(name = "hero_id"),
      inverseJoinColumns = @JoinColumn(name = "villain_id"))
  private Set<Villain> antagonists = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "city_id")
  private City city;

  public Hero(String name, City city) {
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

  public Set<Villain> getAntagonists() {
    return antagonists;
  }

  public void setAntagonists(Set<Villain> antagonists) {
    this.antagonists = antagonists;
  }

  public void setCity(City city) { this.city = city; }

  public City getCity() { return city; }

  @Override
  public String toString() {
    return String.format(
        "Hero[id=%d, name='%s', city='%s']",
        id, name, city);
  }
}
