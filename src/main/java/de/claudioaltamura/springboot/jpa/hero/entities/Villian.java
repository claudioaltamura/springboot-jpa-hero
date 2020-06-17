package de.claudioaltamura.springboot.jpa.hero.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "villians")
public class Villian {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "antagonists")
  private Set<Hero> heroes = new HashSet<>();

  public Villian(String name) {
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

  public Set<Hero> getHeroes() {
    return heroes;
  }

  public void setHeroes(Set<Hero> heroes) {
    this.heroes = heroes;
  }

  @Override
  public String toString() {
    return String.format(
        "Villian[id=%d, name='%s']",
        id, name);
  }
}
