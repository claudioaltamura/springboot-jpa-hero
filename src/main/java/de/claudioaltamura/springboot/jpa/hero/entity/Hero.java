package de.claudioaltamura.springboot.jpa.hero.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "heroes",
		indexes = {
			@Index(name = "CITY_IDX", columnList = "city_id"),
			@Index(name = "MASTER_IDX", columnList = "master_id"),
	}
)
public class Hero {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "heroes_villains",
      joinColumns = @JoinColumn(name = "hero_id"),
      inverseJoinColumns = @JoinColumn(name = "villain_id"))
  private Set<Villain> antagonists = new HashSet<>();

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  private City city;

  @ManyToOne
  private Hero master;
  @OneToMany(mappedBy = "master")
  private List<Hero> sidekicks = new ArrayList<>();

  public Hero() {}

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

  public Hero getMaster() { return master; }

  public void setMaster(Hero master) { this.master = master; }

  public List<Hero> getSidekicks() { return sidekicks; }

  public void setSidekicks(List<Hero> sidekicks) { this.sidekicks = sidekicks; }

  @Override
  public String toString() {
    return String.format(
        "Hero[id=%d, name='%s', city='%s']",
        id, name, city);
  }
}
