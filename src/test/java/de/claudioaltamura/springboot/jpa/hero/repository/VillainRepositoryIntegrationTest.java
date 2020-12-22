package de.claudioaltamura.springboot.jpa.hero.repository;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entity.City;
import de.claudioaltamura.springboot.jpa.hero.entity.Villain;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class VillainRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private VillainRepository villianRepository;

  @Test
  void findById() {
    City metropolis = new City("Metropolis");
    Villain riddler = new Villain("Riddler",  metropolis);

    entityManager.persist(riddler);

    Optional<Villain> villain = villianRepository.findById(riddler.getId());

    assertThat(villain).isNotNull();
  }
}