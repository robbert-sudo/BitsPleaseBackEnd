package bitspleaseApp.repository;

import bitspleaseApp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void initEach() {
        String username = "Bob";
        User user = new User(username, "password", "bob@bob.com");
        User user1 = new User("Rob", "password", "Rob@bob.com");
        underTest.save(user);
        underTest.save(user1);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByUsernameWhenUsernameExists() {
        //arrange


        //act
        Optional<User> expectedUser = underTest.findByUsername("Bob");

        //assert
        assertEquals("Bob", expectedUser.get().getUsername());
    }

    @Test
    void findByUserNameWhenUsernameDoesNotExist() {
        //arrange


        //act
        Optional<User> expectedUser = underTest.findByUsername("Toby");


        //assert
        assertEquals(Optional.empty(), expectedUser);
    }


    @Test
    void findByIdWhenIdExists() {
        //arrange

        //act
        Optional<User> expectedUser = underTest.findById(1);

        //assert
        assertEquals("Bob", expectedUser.get().getUsername());
    }

    @Test
    void findByIdWhenIdDoesNotExist() {
        //arrange

        //act
        Optional<User> expectedUser = underTest.findById(3);

        //assert
        assertFalse(expectedUser.isPresent());
    }

    @Test
    void findAllByEnabled() {
        //arrange

        //act
        Iterable<User> expectedUsers = underTest.findAllByEnabled(true);

        //assert
        int enabledUsers = 0;
        for (User user : expectedUsers) {
            if (user.isEnabled()) {
                enabledUsers++;
            }
        }

        assertEquals(2, enabledUsers);

    }

    @Test
    void findAllByEnabledWhenAnUserIsDisabled() {
        //arrange
        User user3 = new User("Tom", "password", "Tom@bob.com");
        user3.setEnabled(false);
        underTest.save(user3);
        User user4 = new User("Kim", "password", "kim@kim.com");
        user4.setEnabled(false);
        underTest.save(user4);

        //act
        Iterable<User> expectedUsers = underTest.findAllByEnabled(false);

        int disabledUsers = 0;
        for (User user : expectedUsers) {
            if (!user.isEnabled()) {
                disabledUsers++;
            }
        }

        // assert
        assertEquals(2, disabledUsers);
    }
}