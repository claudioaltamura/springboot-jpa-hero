package de.claudioaltamura.springboot.jpa.hero.repositories;

import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {

  List<Hero> findByName(String name);

}
