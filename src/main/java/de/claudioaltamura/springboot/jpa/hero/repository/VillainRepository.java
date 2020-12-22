package de.claudioaltamura.springboot.jpa.hero.repository;

import de.claudioaltamura.springboot.jpa.hero.entity.Villain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VillainRepository extends CrudRepository<Villain, Long> {

  List<Villain> findByName(String name);

}
