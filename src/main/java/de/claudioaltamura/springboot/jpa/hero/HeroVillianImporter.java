package de.claudioaltamura.springboot.jpa.hero;

import de.claudioaltamura.springboot.jpa.hero.entities.City;
import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.entities.Villain;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import de.claudioaltamura.springboot.jpa.hero.service.HeroService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    Hero batman = new Hero("Batman", metropolis);

    Set<Villain> antagonists = new HashSet<>();
    antagonists.add(new Villain("Riddler", metropolis));
    batman.setAntagonists(antagonists);

    List<Hero> sidekicks = new ArrayList<Hero>();
    Hero nightWing = new Hero("Night Wing", metropolis);
    nightWing.setMaster(batman);
    Hero robin = new Hero("Robin",  metropolis);
    robin.setMaster(batman);
    sidekicks.add(nightWing);
    sidekicks.add(robin);
    batman.setSidekicks(sidekicks);

    heroService.add(batman);

    Hero found = heroService.findById(batman.getId());

    System.out.println(found);
  }
}
