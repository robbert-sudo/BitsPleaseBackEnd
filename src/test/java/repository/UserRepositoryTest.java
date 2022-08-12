package repository;

import com.rob.bitspleaseapp.BitspleaseApplication;
import com.rob.bitspleaseapp.model.User;
import com.rob.bitspleaseapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {

        //Arrange
        User user1 = new User("Bob", "password", "bob@bobmail.com");
        User user2 = new User("Rob", "password", "rob@robmail.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        entityManager.clear();
    }


    @Test
    void testFindUserById() {
        //Act
        Optional<User> testUser = userRepository.findByUsername("Bob");
        Long id = testUser.get().getUser_id();
        Optional<User> actualUser = userRepository.findById(id);

        String expectedUserName = "Bob";
        String actualUserName = actualUser.get().getUsername();

        //Assert
        assertEquals(expectedUserName, actualUserName);
    }


    @Test
    void testFindUserByIdWithNoResults() {
        //Act
        int longId = 119;
        Long id = (long) longId;
        Optional<User> actualUser = userRepository.findById(id);

        //Assert
        assertEquals(Optional.empty(), actualUser);
    }


    @Test
    void testFindUserByUserName() {

        //Act
        String expectedEmail = "bob@bobmail.com";

        Optional<User> actualUser = userRepository.findByUsername("Bob");

        String actualEmail = actualUser.get().getEmail();

        //Assert
        assertEquals(expectedEmail, actualEmail);
    }


    @Test
    void testFindUserByUserNameWithNoResults() {
        //Act
        Optional<User> actualUser = userRepository.findByUsername("Bert");

        //Assert
        assertEquals(Optional.empty(), actualUser);
    }


}
