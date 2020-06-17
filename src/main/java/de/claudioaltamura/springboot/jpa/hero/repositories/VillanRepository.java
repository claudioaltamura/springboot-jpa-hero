package de.claudioaltamura.springboot.jpa.hero.repositories;

import de.claudioaltamura.springboot.jpa.hero.entities.Villian;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VillanRepository extends CrudRepository<Villian, Long> {

  List<Villian> findByName(String name);

}
