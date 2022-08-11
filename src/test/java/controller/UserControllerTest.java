package controller;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.dto.request.UserEmailPatchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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


    //    @Test
//    void disableUserTest(@Autowired MockMvc mvc) throws Exception {
//        mvc.perform(patch("/user/delete/1")
//                .with(user("admin")
//                        .password("pass")
//                        .roles("ADMIN"))
//                .contentType("Application/json")
//                .content("{\"email\": \"bob@bob.com\" }"))
//                .andExpect(status().isNoContent());
//
//
//    }


//    @Test
//    void updateUserTest(@Autowired MockMvc mvc) throws Exception {
//        UserEmailPatchRequest email = new UserEmailPatchRequest("bob@bbb.mail");
//        long id = 1;
//
//                        mvc.perform(patch("/user/{user_id}", id)
//                        .with(user("admin")
//                                .password("pass")
//                                .roles("USER"))
//                        .contentType("application/json")
//
//                        .content("{\"email\": \"bob@bbb.mail\"}"))
//                .andExpect(status().isOk());




//        long user_id = 1;
//        String content = "{ \"email\": \"bob@email.mail\" }";
//        MockHttpServletRequestBuilder builder =
//                MockMvcRequestBuilders.patch("/user/" + user_id)
//                        .with(user("admin")
//                                .password("pass")
//                                .roles("USER"))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .characterEncoding("UTF-8")
//                        .content(content);
//
//        mvc.perform(builder)
//                .andExpect(MockMvcResultMatchers.status()
//                        .isOk());

//        mvc.perform(patch("/user/" + user_id)
//                        .with(user("admin")
//                                .password("pass")
//                                .roles("USER"))
//                        .contentType("Application/json")
//                        .content("{\"email\": \"bob@bob.com\"}"))
//                .andExpect(status().isOk());
//    }


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
    void getUserTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/admin")
                        .with(user("admin")
                                .password("pass")
                                .roles("ADMIN")
                        ))
                .andExpect(status().isOk());
    }


    @Test
    void getUserTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/admin"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getUserByIdTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/id/{user_id}", 2)
                        .with(user("admin")
                                .password("pass")
                                .roles("ADMIN")
                        ))
                .andExpect(status().isOk());
    }


    @Test
    void getUserByIdTestWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/id/1"))
                .andExpect(status().isUnauthorized());
    }


}
