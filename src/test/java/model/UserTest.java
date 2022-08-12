package model;

import bitspleaseApp.model.Authority;
import bitspleaseApp.model.Game;
import bitspleaseApp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User("Bob", "password", "Bob@bobmail.com");
    }


    @Test
    public void testGetUserNameAndEmail() {

        //Arrange


        //act
        String expectedNameAndEmail = "Bob Bob@bobmail.com";
        String actualNameAndEmail = this.user.getNameAndEmail();

        // assert
        assertEquals(actualNameAndEmail, expectedNameAndEmail);
    }

    @Test
    public void testGetAuthorities() {

        //Arrange
        Authority authority = new Authority();
        Set<Authority> authorityList = new HashSet<>();

        authority.setUser_id(1);
        authority.setUsername("Bob");
        authority.setAuthority("ROLE_USER");
        authorityList.add(authority);

        user.setAuthorities(authorityList);

        //Act
        Set<Authority> actualAuthority = user.getAuthorities();

        Set<Authority> expectedAuthority = new HashSet<>();
        expectedAuthority.add(authority);

        //Assert
        assertEquals(actualAuthority, expectedAuthority);
    }

    @Test
    public void testGetGames() {
        //Arrange
        Game game = new Game();
        game.setId(1);
        game.setUploader(1);
        game.setUploader_name("Bob");
        game.setName("super mario land");
        game.setSystem("gameboy");
        game.setDeveloper("nintendo");
        BigDecimal price = new BigDecimal("25.50");
        game.setPrice(price);

        Set<Game> games = new HashSet<>();
        games.add(game);
        user.setGames(games);

        //Act
        Set<Game> actualGames = new HashSet<>();
        actualGames = user.getGames();

        //Assert
        assertEquals(actualGames, games);

    }
}
