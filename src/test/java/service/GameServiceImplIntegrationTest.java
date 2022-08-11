package service;

import bitspleaseApp.BitspleaseApplication;
import bitspleaseApp.model.Game;
import bitspleaseApp.repository.GameRepository;
import bitspleaseApp.service.GameService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;



@SpringBootTest
@ContextConfiguration(classes = {BitspleaseApplication.class})
public class GameServiceImplIntegrationTest {

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    @Mock
    Game game;
    Game game2;


}
