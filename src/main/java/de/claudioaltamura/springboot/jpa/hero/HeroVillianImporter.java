package de.claudioaltamura.springboot.jpa.hero;

import de.claudioaltamura.springboot.jpa.hero.entities.City;
import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import de.claudioaltamura.springboot.jpa.hero.service.HeroService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HeroVillianImporter implements CommandLineRunner {

  @Autowired
  private HeroService heroService;

  @Override
  public void run(String... args) {
    City metropolis = new City("Metropolis");
    Hero hero = new Hero("Batman", metropolis);

    Set<Villain> antagonists = new HashSet<>();
    antagonists.add(new Villain("Riddler", metropolis));

    hero.setAntagonists(antagonists);

    heroService.add(hero);

    Hero found = heroService.findById(hero.getId());

    System.out.println(found);
  }
}
