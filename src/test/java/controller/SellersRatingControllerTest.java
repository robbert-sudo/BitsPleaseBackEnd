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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
@AutoConfigureMockMvc
public class SellersRatingControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void addSellerRatingTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/sellerratings")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER"))
                        .contentType("Application/json")
                        .content("{\"ratedUserId\": 1, \"rating\": 9 }"))
                .andExpect(status().isOk());
    }


    @Test
    void addSellerRatingWithoutAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/sellerratings")
                        .contentType("Application/json")
                        .content("{\"ratedUserId\": 1, \"rating\": 9 }"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getAllRatingsTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")))
                .andExpect(status().isOk());
    }


    @Test
    void getAllRatingsWithoutAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void getAllRatingsFromUserTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings/1")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")))
                .andExpect(status().isOk());
    }


    @Test
    void getAllRatingsFromUserWithoutAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings/1"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void geAverageRatingFromUserTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings/getaverage/1")
                        .with(user("admin")
                                .password("pass")
                                .roles("USER")))
                .andExpect(status().isOk());
    }


    @Test
    void geAverageRatingFromUserWithoutAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/sellerratings/getaverage/1"))
                .andExpect(status().isUnauthorized());
    }

}
