package bitspleaseApp.service;

import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.UserRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    User user;

    @Test
    public void testFindAll() {

        Set<User> usersSet = new HashSet<>();


        User user1 = new User();
        user1.setUser_id(1);
        user1.setUsername("admin");
        user1.setPassword("password");
        user1.setEnabled(true);
        user1.setEmail("admin@ad.min");
        user1.setAuthorities(null);
        usersSet.add(user1);

        User user2 = new User();
        user2.setUser_id(2);
        user2.setUsername("user");
        user2.setPassword("password");
        user2.setEnabled(true);
        user2.setEmail("user@us.er");
        user2.setAuthorities(null);
        usersSet.add(user2);

        User user3 = new User();
        user3.setUser_id(3);
        user3.setUsername("bob");
        user3.setPassword("password");
        user3.setEnabled(false);
        user3.setEmail("bob@bob.nl");
        user3.setAuthorities(null);
        usersSet.add(user3);


        Iterable<User> users = usersSet;

        Mockito
                .when(userRepository.findAll())
                .thenReturn(users);

        //act
        Set<String> expected = new HashSet<>();
        String first = "admin";
        String second = "user";
        String third = "bob";
        expected.add(first);
        expected.add(second);
        expected.add(third);

        Iterable<UserDetailsResponse> response = userService.findAll();

        Set<String> actual = new HashSet<>();

        for (UserDetailsResponse userDetailsResponse : response) {
            String firstname = userDetailsResponse.getUsername();
            actual.add(firstname);

        }

        //assert
        assertEquals(actual, expected);


    }


}
