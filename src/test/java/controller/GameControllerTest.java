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
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void postGameTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/games")
                .contentType("application/json")
                .content("{\"name\": \"mario\", \"uploader\": 1, \"uploader_name\": \"admin\", \"price\": 25.00 }")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void postGameWithoutAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/games")
                .contentType("application/json")
                .content("{\"name\": \"mario\", \"uploader\": 1, \"uploader_name\": \"admin\", \"price\": 25.00 }")
        ).andExpect(status().isUnauthorized());
    }


    @Test
    void getGameTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void getGameWithNameParamTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games?name=mario")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void getGameWithNameParamNoAuthTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games?name=mario"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void findGameByIdTest(@Autowired MockMvc mvc) throws Exception {
        long param = 1;
        Long id = param;
        mvc.perform(get("/games/id/{id}", id)
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void findGamesBySystemTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games/system/snes")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void findGamesBySystemAndNameTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games/systemandname/snes?name=mario")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void findGamesBySystemAndNameTestNoAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games/systemandname/snes?name=mario"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void findGamesByUploaderId(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games/uploader/1")
                .with(user("admin")
                        .password("pass")
                        .roles("ADMIN"))
        ).andExpect(status().isOk());
    }


    @Test
    void findGamesByUploaderIdWithoutAuth(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/games/uploader/1"))
                .andExpect(status().isUnauthorized());
    }


}
