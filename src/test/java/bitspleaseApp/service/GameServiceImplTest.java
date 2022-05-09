package bitspleaseApp.service;

import bitspleaseApp.model.Game;
import bitspleaseApp.repository.GameRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceImplTest {

    @Test
    void findAll() {
        //arrange

        GameRepository gameRepository = mock(GameRepository.class);

        ArrayList<Game> fakeResults = new ArrayList<>();
        Game game = new Game();
        game.setId(1);
        game.setName("super mario land");
        game.setSystem("gameboy");
        game.setDeveloper("nintendo");
        game.setUploader_id(1);
        game.setUploader_name("admin");
        game.setPrice(new BigDecimal("25.50"));
        fakeResults.add(game);

        Game game2 = new Game();
        game.setId(2);
        game.setName("super metroid");
        game.setSystem("snes");
        game.setDeveloper("nintendo");
        game.setUploader_id(1);
        game.setUploader_name("admin");
        game.setPrice(new BigDecimal("75.50"));
        fakeResults.add(game2);

        Game game3 = new Game();
        game.setId(3);
        game.setName("super mario kart");
        game.setSystem("snes");
        game.setDeveloper("nintendo");
        game.setUploader_id(2);
        game.setUploader_name("user");
        game.setPrice(new BigDecimal("85.50"));
        fakeResults.add(game3);

        when(gameRepository.findAll())
                .thenReturn(fakeResults);

        GameServiceImpl gameServiceImpl = new GameServiceImpl();

        //act
        Iterable<Game> games = gameRepository.findAll();
        float size = ((Collection<?>) games).size();


        //assert
        assertEquals(3, size);
    }


    @Test
    void findByName() {

    }


}
