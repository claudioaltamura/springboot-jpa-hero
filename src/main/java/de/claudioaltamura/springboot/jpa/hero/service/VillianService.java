package de.claudioaltamura.springboot.jpa.hero.service;

import de.claudioaltamura.springboot.jpa.hero.entity.Villain;
import de.claudioaltamura.springboot.jpa.hero.repository.VillainRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VillianService {

  private final VillainRepository villainRepository;

  public VillianService(VillainRepository villainRepository) {
  	this.villainRepository = villainRepository;
  }

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
