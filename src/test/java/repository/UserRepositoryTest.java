package repository;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.UserRepository;
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


    @Test
    void testFindUserByUserName() {
        User user1 = new User("Bob", "password", "bob@bobmail.com");
        User user2 = new User("Rob", "password", "rob@robmail.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        String expectedEmail = "bob@bobmail.com";

        Optional<User> actualUser = userRepository.findByUsername("Bob");

        String actualEmail = actualUser.get().getEmail();

        assertEquals(expectedEmail, actualEmail);
    }


    @Test
    void testFindUserByUserNameWithNoResults() {
        User user1 = new User("Bob", "password", "bob@bobmail.com");
        User user2 = new User("Rob", "password", "rob@robmail.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        Optional<User> actualUser = userRepository.findByUsername("Bert");

        assertEquals(Optional.empty(), actualUser);
    }

    @Test
    void testFindUserById() {  ///kijken naar findById()
        User user1 = new User("Bob", "password", "bob@bobmail.com");
        user1.setUser_id(1);
        User user2 = new User("Rob", "password", "rob@robmail.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        Optional<User> actualUser = userRepository.findById(1);
//        Optional<User> actualUser2 = userRepository.findById(2);

        String expectedUserName = "Bob";
        String actualUserName = actualUser.get().username;

//        String expectedUserName2 = "Rob";
//        String actualUserName2 = actualUser2.get().username;

        assertEquals(expectedUserName, actualUserName);


    }

}
