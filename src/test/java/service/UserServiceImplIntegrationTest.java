package service;


import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.UserRepository;
import bitspleaseApp.service.UserService;
import bitspleaseApp.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    User user;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("test of user_id van opgehaalde user klopt")
    public void testGetUserByUserName() {
        user = new User();
        user.setUser_id(1);
        user.setUsername("Bob");
        user.setEmail("Bob@bobmail.com");

        Mockito
                .when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.ofNullable(user));

        String userName = "Bob";
        long expectedUser_id = 1;

        UserDetailsResponse found = userService.getUser(userName);
        assertEquals(found.getUser_id(), expectedUser_id);

    }

}
