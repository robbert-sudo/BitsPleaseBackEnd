package controller;

import com.rob.bitspleaseapp.BitspleaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;



    @Test
    void createUserTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/user")
                        .contentType("Application/json")
                        .content("{\"username\": \"robbert\", \"password\":\"password\", \"email\":\"bob@bobmail.com\"}")
                )
                .andExpect(status().isCreated());
    }


    @Test
    void updateUserTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(patch("/user/admin")
                        .contentType("Application/json")
                        .content("{\"email\": \"bob@bob.com\"}"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUsersTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/users")
                        .with(user("admin")
                                .password("pass")
                                .roles("ADMIN")))
                .andExpect(status().isOk());
    }


    @Test
    void getUsersTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUserByUserNameUserNotFoundExceptionTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/{username}", "bert")
                        .with(user("admin")
                                .password("pass")
                                .roles("ADMIN")
                        ))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUserTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/admin"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUserByIdWithNonExistingUserIdTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/id/{user_id}", 5)
                        .with(user("admin")
                                .password("pass")
                                .roles("ADMIN")
                        ))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUserByIdTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/id/1"))
                .andExpect(status().isUnauthorized());
    }


}
