package de.claudioaltamura.springboot.jpa.hero.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entities.City;
import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillainRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class VillainRepositoryIntegrationTest {

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