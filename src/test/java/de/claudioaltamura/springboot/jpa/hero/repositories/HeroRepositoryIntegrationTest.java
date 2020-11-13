package de.claudioaltamura.springboot.jpa.hero.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entities.City;
import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.entities.Villain;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class HeroRepositoryIntegrationTest {

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

  @Test
  void multipleHeroes() {
    Hero batman = new Hero("Batman", metropolis);
    Hero robin = new Hero("robin", metropolis);

    heroRepository.save(batman);
    heroRepository.save(robin);

    assertThat(heroRepository.count()).isEqualTo(2L);

    heroRepository.delete(robin);

    Hero found = heroRepository.findById(batman.getId()).get();

    assertThat(found.getCity().getName()).isEqualTo("Metropolis");
  }

  @Test
  void sidekicks() {
    Hero batman = new Hero("Batman", metropolis);

    List<Hero> sidekicks = new ArrayList<Hero>();
    Hero nightWing = new Hero("Night Wing", metropolis);
    nightWing.setMaster(batman);
    Hero robin = new Hero("Robin",  metropolis);
    robin.setMaster(batman);
    sidekicks.add(nightWing);
    sidekicks.add(robin);
    batman.setSidekicks(sidekicks);

    entityManager.persist(batman);

    Optional<Hero> foundHero = heroRepository.findById(batman.getId());
    assertThat(foundHero.isPresent());
    assertThat(foundHero.get().getSidekicks().size()).isEqualTo(2);
  }

}