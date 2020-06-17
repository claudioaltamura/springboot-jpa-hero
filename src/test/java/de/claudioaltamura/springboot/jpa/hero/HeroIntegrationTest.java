package de.claudioaltamura.springboot.jpa.hero;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.entities.Villian;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillanRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class HeroIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private HeroRepository heroRepository;

  @Autowired
  private VillanRepository villanRepository;

  @Test
  void findById() {
    Hero hero = new Hero("Batman");

    Set<Villian> antagonists = new HashSet<>();
    antagonists.add(new Villian("Riddler"));

    hero.setAntagonists(antagonists);

    entityManager.persist(hero);

    Optional<Hero> foundHero = heroRepository.findById(hero.getId());

    assertThat(foundHero).isNotNull();
    assertThat(foundHero.get().getAntagonists()).isNotEmpty();
  }

  @Test
  void saveAntagonist() {
    Hero hero = new Hero("Batman");

    Set<Villian> antagonists = new HashSet<>();
    antagonists.add(new Villian("Riddler"));

    hero.setAntagonists(antagonists);

    entityManager.persist(hero);

    List<Villian> villians = villanRepository.findByName("Riddler");

    assertThat(villians).isNotNull();
    assertThat(villians).hasSize(1);
    assertThat(villians.get(0).getName()).isEqualTo("Riddler");
  }

  @Test
  void findByName() {
    List<Hero> heroes = heroRepository.findByName("Superman");

    assertThat(heroes).isNotNull();
    assertThat(heroes).isEmpty();
  }

}