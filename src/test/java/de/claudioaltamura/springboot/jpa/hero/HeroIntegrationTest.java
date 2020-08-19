package de.claudioaltamura.springboot.jpa.hero;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entities.City;
import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillainRepository;
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
  private VillainRepository villainRepository;

  private City metropolis = new City("Metropolis");

  @Test
  void findById() {
    Hero hero = new Hero("Batman", metropolis);

    Set<Villain> antagonists = new HashSet<>();
    antagonists.add(new Villain("Riddler", metropolis));

    hero.setAntagonists(antagonists);

    entityManager.persist(hero);

    Optional<Hero> foundHero = heroRepository.findById(hero.getId());

    assertThat(foundHero).isNotNull();
    assertThat(foundHero.get().getAntagonists()).isNotEmpty();
  }

  @Test
  void saveAntagonist() {
    Hero hero = new Hero("Batman", metropolis);

    Set<Villain> antagonists = new HashSet<>();
    antagonists.add(new Villain("Riddler", metropolis));

    hero.setAntagonists(antagonists);

    entityManager.persist(hero);

    List<Villain> villains = villainRepository.findByName("Riddler");

    assertThat(villains).isNotNull();
    assertThat(villains).hasSize(1);
    assertThat(villains.get(0).getName()).isEqualTo("Riddler");
  }

  @Test
  void findByName() {
    List<Hero> heroes = heroRepository.findByName("Superman");

    assertThat(heroes).isNotNull();
    assertThat(heroes).isEmpty();
  }

}