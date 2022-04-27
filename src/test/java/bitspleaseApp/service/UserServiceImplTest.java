package bitspleaseApp.service;

import bitspleaseApp.dto.response.UserDetailsResponse;
import bitspleaseApp.model.User;
import bitspleaseApp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Test
    void findAll() {
        //arrange

        UserRepository userRepository = mock(UserRepository.class);

        ArrayList<User> fakeresult = new ArrayList<User>();
        User fakeUser = new User("admin", "password",  "admin@ad.min");
        User fakeUser2 = new User("user", "password", "user@useless.nl");
        fakeresult.add(fakeUser);
        fakeresult.add(fakeUser2);
        when(userRepository.findAll())
                .thenReturn(fakeresult);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository);

        //act
        Set<UserDetailsResponse> result = userServiceImpl.findAll();
        String expected = "[bitspleaseApp.dto.response.UserDetailsResponse@21baa903, bitspleaseApp.dto.response.UserDetailsResponse@7fc6de5b]";

        //assert
        assertEquals(expected , result);
    }

    @Test
    void getUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void disableUser() {
    }

    @Test
    void findAllByDisabled() {
    }
}