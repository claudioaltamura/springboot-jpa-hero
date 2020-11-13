package de.claudioaltamura.springboot.jpa.hero.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.claudioaltamura.springboot.jpa.hero.entities.Hero;
import de.claudioaltamura.springboot.jpa.hero.repositories.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class HeroServiceTest {

    private HeroRepository heroRepository = Mockito.mock(HeroRepository.class);

    private HeroService heroService;

    @BeforeEach
    public void setUp() {
        this.heroService = new HeroService(heroRepository);
    }

    @Test
    void findById() {
        when(heroRepository.findById(42L)).thenReturn(hero());

        Hero foundHero = heroService.findById(42L);

        assertThat(foundHero).isNotNull();
        verify(heroRepository).findById(42L);
    }

    private Optional<Hero> hero() {
        Hero hero = new Hero("Batman", null);
        hero.setId(42L);
        return Optional.of(hero);
    }

}
