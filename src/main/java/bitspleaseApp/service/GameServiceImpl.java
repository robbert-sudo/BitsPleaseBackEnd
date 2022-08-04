package bitspleaseApp.service;

import bitspleaseApp.repository.GameRepository;
import bitspleaseApp.exceptions.RecordNotFoundException;
import bitspleaseApp.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;


    public Iterable<Game> findAll() {
        Iterable<Game> games = gameRepository.findAll();
        return games;
    }

    public Iterable<Game> findByName(String name) {
        return gameRepository.findByNameContains(name);
    }

    public Iterable<Game> findBySystemAndNameContains(String system, String name) {
      Iterable<Game> games = gameRepository.findBySystemAndNameContains(system, name);
      return games;
    }

    public Optional<Game> findById(long id) { return gameRepository.findById(id); }

    public Iterable<Game> findBySystem(String system) {
        return gameRepository.findBySystem(system);
    }

    public Optional<Game> save(Game game) {
        gameRepository.save(game);
        Optional<Game> savedGame = findById(game.id);
        return savedGame;
    }

    public void deleteById(long id) {
        try {
            gameRepository.deleteById(id);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex);
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateGame(long id, Game game) {
        if (!gameRepository.existsById(id)) throw new RecordNotFoundException();
        Game existingGame = gameRepository.findById(id).get();
        existingGame.setName(game.getName());
        existingGame.setSystem(game.getSystem());
        existingGame.setDeveloper(game.getDeveloper());
        existingGame.setUploader_id(game.getUploader_id());
        existingGame.setUploader_name(game.getUploader_name());
        existingGame.setPrice(game.getPrice());
        existingGame.setImage(game.getImage());
        gameRepository.save(existingGame);

    }


}
