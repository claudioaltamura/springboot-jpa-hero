package de.claudioaltamura.springboot.jpa.hero.service;

import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import de.claudioaltamura.springboot.jpa.hero.exceptions.EntityNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HeroService {

  @Autowired
  private HeroRepository heroRepository;

  public Hero add(Hero hero) {
    return heroRepository.save(hero);
  }

  public List<Hero> findAll() {
    return (List<Hero>) heroRepository.findAll();
  }

  public List<Hero> findByName(String name) { return heroRepository.findByName(name); }

  public Hero findById(Long id) {
    return heroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public void deleteById(Long id) {
    heroRepository.deleteById(id);
  }

}
