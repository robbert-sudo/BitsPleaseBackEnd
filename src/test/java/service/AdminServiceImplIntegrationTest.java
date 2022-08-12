package service;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.AdminRepository;
import bitspleaseApp.service.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class AdminServiceImplIntegrationTest {

    @Autowired
    AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;

    @Mock
    User user;
    User user2;



    @Test
    public void testFindAllByDisabled() {

        user = new User("Bob", "pass", "e@mail.com");
        user.setUser_id(1);
        user.setEnabled(false);

        ArrayList<User> fakeUsers = new ArrayList<>();
        fakeUsers.add(user);

        Mockito
                .when(adminRepository.findAllByEnabled(false))
                .thenReturn(fakeUsers);


        Set<UserDetailsResponse> userDetailsResponse = adminService.findAllByDisabled();

        int expectedLength = 1;


        assertFalse(userDetailsResponse.isEmpty());
        assertEquals(expectedLength, userDetailsResponse.size());
    }


    @Test
    public void testFindAllByDisabledWithTwoResults() {

        user = new User("Bob", "pass", "e@mail.com");
        user.setUser_id(1);
        user.setEnabled(false);

        user2 = new User("Rob", "pass", "em@mail.com");
        user2.setUser_id(2);
        user2.setEnabled(false);

        ArrayList<User> fakeUsers = new ArrayList<>();
        fakeUsers.add(user);
        fakeUsers.add(user2);

        Mockito
                .when(adminRepository.findAllByEnabled(false))
                .thenReturn(fakeUsers);


        Set<UserDetailsResponse> userDetailsResponse = adminService.findAllByDisabled();

        int expectedLength = 2;


        assertFalse(userDetailsResponse.isEmpty());
        assertEquals(expectedLength, userDetailsResponse.size());
    }

}
