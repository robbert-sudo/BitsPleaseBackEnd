package controller;


import bitspleaseApp.BitspleaseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;



    @Test
    void getDisabledUsersTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/admin/deletedusers")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN")))
                .andExpect(status().isOk());
    }


    @Test
    void getDisabledUsersTestWithWrongUserRole(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/admin/deletedusers")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")))
                .andExpect(status().isForbidden());
    }


    @Test
    void deleteUserTest() throws Exception {
        mvc.perform(delete("/admin/delete/Bob")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN")))
                .andExpect(status().isOk());
    }


    @Test
    void deleteUserTestWithoutAuthorization() throws Exception {
        mvc.perform(delete("/admin/delete/Bob"))
                                .andExpect(status().isUnauthorized());
    }


    @Test
    void deleteUserTestWithoutAdminRole() throws Exception {
        mvc.perform(delete("/admin/delete/Bob")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")))
                .andExpect(status().isForbidden());
    }


}
