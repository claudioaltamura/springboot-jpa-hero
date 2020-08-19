package de.claudioaltamura.springboot.jpa.hero.service;

import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import de.claudioaltamura.springboot.jpa.hero.exceptions.EntityNotFoundException;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillainRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VillianService {

  @Autowired
  private VillainRepository villainRepository;

  public Villain add(Villain villain) {
    return villainRepository.save(villain);
  }

  public List<Villain> findAll() {
    return (List<Villain>) villainRepository.findAll();
  }

  public List<Villain> findByName(String name) { return villainRepository.findByName(name); }

  public Villain findById(Long id) {
    return villainRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id));
  }

  public void deleteById(Long id) {
    villainRepository.deleteById(id);
  }

}
