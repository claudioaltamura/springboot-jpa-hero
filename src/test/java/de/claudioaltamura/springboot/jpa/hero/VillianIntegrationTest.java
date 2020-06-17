package de.claudioaltamura.springboot.jpa.hero;

import static org.assertj.core.api.Assertions.assertThat;

import de.claudioaltamura.springboot.jpa.hero.entities.Villian;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillanRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class VillianIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private VillanRepository villianRepository;

  @Test
  void findById() {
    Villian riddler = new Villian("Riddler");

    entityManager.persist(riddler);

    Optional<Villian> villian = villianRepository.findById(riddler.getId());

    assertThat(villian).isNotNull();
  }
}