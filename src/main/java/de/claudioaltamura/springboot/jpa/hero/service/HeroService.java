package de.claudioaltamura.springboot.jpa.hero.service;

import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import de.claudioaltamura.springboot.jpa.hero.exceptions.EntityNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HeroService {

  private HeroRepository heroRepository;

  public HeroService(HeroRepository heroRepository) {
      this.heroRepository = heroRepository;
  }

  public Hero add(Hero hero) {
    return heroRepository.save(hero);
  }

  public Hero update(Hero heroToUpdate, long id) {
    return heroRepository.findById(id)
            .map(hero -> {
              hero.setName(heroToUpdate.getName());
              hero.setAntagonists(heroToUpdate.getAntagonists());
              hero.setCity(heroToUpdate.getCity());
              hero.setSidekicks(heroToUpdate.getSidekicks());
              return heroRepository.save(hero);
            })
            .orElseGet(() -> {
              heroToUpdate.setId(id);
              return heroRepository.save(heroToUpdate);
            });
  }

  public List<Hero> findAll() {
    return (List<Hero>) heroRepository.findAll();
  }

  public List<Hero> findByName(String name) { return heroRepository.findByName(name); }

  public Hero findById(Long id) {
    return heroRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id));
  }

  public void deleteById(Long id) {
    heroRepository.deleteById(id);
  }

}
