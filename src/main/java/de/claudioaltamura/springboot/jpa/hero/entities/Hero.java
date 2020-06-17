package de.claudioaltamura.springboot.jpa.hero.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
public class Hero {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "heroes_vilians",
      joinColumns = @JoinColumn(name = "hero_id"),
      inverseJoinColumns = @JoinColumn(name = "villian_id"))
  private Set<Villian> antagonists = new HashSet<>();

  public Hero(String name) {
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

  public Set<Villian> getAntagonists() {
    return antagonists;
  }

  public void setAntagonists(Set<Villian> antagonists) {
    this.antagonists = antagonists;
  }

  @Override
  public String toString() {
    return String.format(
        "Hero[id=%d, name='%s']",
        id, name);
  }
}
