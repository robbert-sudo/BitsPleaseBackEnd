package service;

import com.rob.bitspleaseapp.BitspleaseApplication;
import com.rob.bitspleaseapp.dto.response.UserDetailsResponse;
import com.rob.bitspleaseapp.model.User;
import com.rob.bitspleaseapp.repository.UserRepository;
import com.rob.bitspleaseapp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

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


    @Test
    @DisplayName("")
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

        UserDetailsResponse found = userService.getUserByUserName(userName);
        assertEquals(found.getUser_id(), expectedUser_id);
    }

}
