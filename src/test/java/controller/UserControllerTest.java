package controller;

import bitspleaseApp.BitspleaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getUsersTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/user/bob")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")
                ))
                .andExpect(status().isOk());
    }

}
