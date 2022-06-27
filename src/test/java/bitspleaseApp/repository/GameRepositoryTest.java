package bitspleaseApp.repository;

import bitspleaseApp.model.Game;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameRepositoryTest {

    @Autowired
    private GameRepository underTest;

    @BeforeEach
    void setUp() {
        BigDecimal bigDecimal = new BigDecimal("25.50");
        Game game = new Game("super mario land", "gameboy", 1, "Bob", bigDecimal);
        underTest.save(game);

        Game game1 = new Game("super mario world", "snes", 1, "Bob", new BigDecimal("35.95"));
        underTest.save(game1);

        Game game2 = new Game("donkey kong country", "snes", 2, "Rob", new BigDecimal("55.95"));
        underTest.save(game2);

        Game game3 = new Game("sonic 2", "megadrive", 1, "Bob", new BigDecimal("45.75"));
        underTest.save(game3);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByNameContains() {
        //arrange
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add("super mario land");
        expectedNames.add("super mario world");

        //act
        Iterable<Game> foundGames = underTest.findByNameContains("mario");

        ArrayList<String> actualGameNames = new ArrayList<>();
        for (Game game : foundGames) {
            actualGameNames.add(game.getName());
        }
        //assert
        assertEquals(actualGameNames, expectedNames);
    }

    @Test
    void findByNameContainsWithNoResults() {
        //arrange
        ArrayList<String> expectedGames = new ArrayList();

        //act
        Iterable<Game> foundGames = underTest.findByNameContains("castlevania");
        //assert
        assertEquals(expectedGames, foundGames);
    }

    @Test
    void findBySystem() {
        //arrange
        ArrayList<String> expectedGameNames = new ArrayList<>();
        expectedGameNames.add("super mario world");
        expectedGameNames.add("donkey kong country");

        //act
        Iterable<Game> foundGames = underTest.findBySystem("snes");

        ArrayList<String> actualGameNames = new ArrayList<>();
        for (Game game : foundGames) {
            actualGameNames.add(game.getName());
        }

        //assert
        assertEquals(expectedGameNames, actualGameNames);
    }

    @Test
    void findBySystemWithNoResults() {
        //arrange
        ArrayList<Game> expectedGames = new ArrayList<>();

        //act
        Iterable<Game> foundGames = underTest.findBySystem("genesis");

        //assert
        assertEquals(expectedGames, foundGames);
    }
}