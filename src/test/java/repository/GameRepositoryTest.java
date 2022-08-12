package repository;

import com.rob.bitspleaseapp.BitspleaseApplication;
import com.rob.bitspleaseapp.model.Game;
import com.rob.bitspleaseapp.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
class GameRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        //Arrange
        Game game1 = new Game("super mario land", "gameboy", 1, "Bob", new BigDecimal("25.50"));
        Game game2 = new Game("super mario world", "snes", 1, "Bob", new BigDecimal("35.95"));
        Game game3 = new Game("donkey kong country", "snes", 2, "Rob", new BigDecimal("55.95"));
        Game game4 = new Game("sonic 2", "megadrive", 1, "Bob", new BigDecimal("45.75"));
        entityManager.persist(game1);
        entityManager.persist(game2);
        entityManager.persist(game3);
        entityManager.persist(game4);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }


    @Test
    void testFindByNameContains() {

        //act
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add("super mario land");
        expectedNames.add("super mario world");

        Iterable<Game> actualGames = gameRepository.findByNameContains("mario");

        ArrayList<String> actualNames = new ArrayList<>();
        for (Game game : actualGames) {
            actualNames.add(game.getName());
        }

        //assert
        assertEquals(expectedNames, actualNames);

    }


    @Test
    @DisplayName("should returns empty ArrayList")
    void findByNameContainsWithNoResults() {
        //arrange
        ArrayList<String> expectedGames = new ArrayList();

        //act
        Iterable<Game> foundGames = gameRepository.findByNameContains("castlevania");

        //assert
        assertEquals(expectedGames, foundGames);
    }


    @Test
    void findBySystem() {
        //act
        ArrayList<String> expectedGames = new ArrayList<>();
        String expectedName = "super mario world";
        String expectedName2 = "donkey kong country";
        expectedGames.add(expectedName);
        expectedGames.add(expectedName2);

        Iterable<Game> foundGames = gameRepository.findBySystem("snes");

        ArrayList<String> actualGames = new ArrayList<>();
        for (Game game : foundGames) {
            actualGames.add(game.getName());
        }

        //Assert
        assertEquals(expectedGames, actualGames);
    }


    @Test
    void findBySystemWithNoResults() {
        //act
        ArrayList<String> expectedGames = new ArrayList<>();

        Iterable<Game> foundGames = gameRepository.findBySystem("gamecube");

        //Assert
        assertEquals(expectedGames, foundGames);
    }



    @Test
    void findBySystemAndNameContains() {
        //Act
        Iterable<Game> actualGames = gameRepository.findBySystemAndNameContains("snes", "mario");

        String expectedName = "super mario world";

        ArrayList<String> actualNames = new ArrayList<>();
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add(expectedName);

        for (Game game : actualGames) {
            actualNames.add(game.getName());
        }

        //Assert
        assertEquals(expectedNames, actualNames);
    }

    @Test
    void findBySystemAndNameContainsWithNoResults() {
        //Act
        Iterable<Game> actualGames = gameRepository.findBySystemAndNameContains("gamecube", "mario");

        ArrayList<String> expectedGames = new ArrayList<>();

        //Assert
        assertEquals(expectedGames, actualGames);
    }

    @Test
    void findAllByUploader() {
        //Act
        Iterable<Game> actualGames = gameRepository.findAllByUploader(1);

        Game game1 = new Game("super mario land", "gameboy", 1, "Bob", new BigDecimal("25.50"));
        Game game2 = new Game("super mario world", "snes", 1, "Bob", new BigDecimal("35.95"));
        Game game4 = new Game("sonic 2", "megadrive", 1, "Bob", new BigDecimal("45.75"));

        ArrayList<Game> expectedGames = new ArrayList<>();
        expectedGames.add(game1);
        expectedGames.add(game2);
        expectedGames.add(game4);
        ArrayList<String> expectedGameNames = new ArrayList<>();
        for (Game game : expectedGames) {
            expectedGameNames.add(game.getName());
        }

        ArrayList<String> actualGameNames = new ArrayList<>();
        for (Game game : actualGames) {
            actualGameNames.add(game.getName());
        }

        //Assert
        assertEquals(expectedGameNames, actualGameNames);
    }


    @Test
    void findAllByUploaderWithNoResults() {
        //Act
        Iterable<Game> actualGames = gameRepository.findAllByUploader(3);

        ArrayList<Game> expectedGames = new ArrayList<>();


        //Assert
        assertEquals(expectedGames, actualGames);
    }

}
