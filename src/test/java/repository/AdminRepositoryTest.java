package repository;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class AdminRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        //Arrange
        User user1 = new User("Bob", "password", "email");
        user1.setEnabled(false);
        User user2 = new User("user", "password", "adres");
        User user3 = new User("admin", "password", "emailadres");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }


    @Test
    void testFindAllByEnabled() {
        //Act
        Iterable<User> actualDisabledUsers = adminRepository.findAllByEnabled(false);

        Optional<User> expectedUser = adminRepository.findByUsername("Bob");

        String expectedUserName = expectedUser.get().username;

        ArrayList<String> actualDisabledUserNames = new ArrayList<>();

        for (User user : actualDisabledUsers) {
            actualDisabledUserNames.add(user.getUsername());
        }

        //Assert
        assertEquals(1, actualDisabledUserNames.size());
        assertEquals(expectedUserName, actualDisabledUserNames.get(0));
    }


    @Test
    void testFindByUsername() {
        //Act
        Optional<User> actualUser = adminRepository.findByUsername("user");

        //Assert
        assertTrue(actualUser.isPresent());
    }


    @Test
    void testFindByUsernameWithNoResult() {
        //Act
        Optional<User> actualUser = adminRepository.findByUsername("Bennie");

        //Assert
        assertTrue(actualUser.isEmpty());
    }

}
