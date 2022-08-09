package Model;

import bitspleaseApp.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void testGetDeveloper() {

        //Arrange
        Game game = new Game();
        game.setName("super mario land");
        game.setSystem("gameboy");
        game.setDeveloper("nintendo");

        //act
        String actualDeveloper = game.getDeveloper();

        //Assert
        String expectedDeveloper = "nintendo";

        assertEquals(actualDeveloper, expectedDeveloper);
    }


}
