package de.claudioaltamura.springboot.jpa.hero.repositories;

import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VillainRepository extends CrudRepository<Villain, Long> {

  List<Villain> findByName(String name);

}
