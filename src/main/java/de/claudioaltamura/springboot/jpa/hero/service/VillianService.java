package de.claudioaltamura.springboot.jpa.hero.service;

import de.claudioaltamura.springboot.jpa.hero.entities.Villian;
import de.claudioaltamura.springboot.jpa.hero.exceptions.EntityNotFoundException;
import de.claudioaltamura.springboot.jpa.hero.repositories.VillanRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VillianService {

  @Autowired
  private VillanRepository villianRepository;

  public Villian add(Villian villian) {
    return villianRepository.save(villian);
  }

  public List<Villian> findAll() {
    return (List<Villian>) villianRepository.findAll();
  }

  public List<Villian> findByName(String name) { return villianRepository.findByName(name); }

  public Villian findById(Long id) {
    return villianRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id));
  }

  public void deleteById(Long id) {
    villianRepository.deleteById(id);
  }

}
