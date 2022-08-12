package com.rob.bitspleaseapp.service;

import com.rob.bitspleaseapp.dto.request.GamePatchRequest;
import com.rob.bitspleaseapp.model.Game;
import com.rob.bitspleaseapp.repository.GameRepository;
import com.rob.bitspleaseapp.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Game> findById(long id) {
        return gameRepository.findById(id);
    }

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
    public Optional<Game> updateGame(long id, Game game) {
        if (!gameRepository.existsById(id)) throw new RecordNotFoundException();
        Game existingGame = gameRepository.findById(id).get();
        existingGame.setName(game.getName());
        existingGame.setSystem(game.getSystem());
        existingGame.setDeveloper(game.getDeveloper());
        existingGame.setUploader(game.getUploader());
        existingGame.setUploader_name(game.getUploader_name());
        existingGame.setPrice(game.getPrice());
        existingGame.setImage(game.getImage());
        gameRepository.save(existingGame);
        Optional<Game> savedGame = findById(id);

        return savedGame;

    }

    @Override
    public void patchGame(long id, GamePatchRequest gamePatchRequest) {
        if (!gameRepository.existsById(id)) throw new RecordNotFoundException();
        Game existingGame = gameRepository.findById(id).get();

        if (gamePatchRequest.getName() != "") {
            existingGame.setName(gamePatchRequest.getName());
        }
        if (gamePatchRequest.getSystem() != "") {
            existingGame.setSystem(gamePatchRequest.getSystem());
        }
        if (gamePatchRequest.getDeveloper() != "") {
            existingGame.setDeveloper(gamePatchRequest.getDeveloper());
        }
        if (gamePatchRequest.getPrice() != null) {
            existingGame.setPrice(gamePatchRequest.getPrice());
        }
        gameRepository.save(existingGame);

    }

    public Iterable<Game> findAllByUploader(long uploader) {
        Iterable<Game> games = gameRepository.findAllByUploader(uploader);
        return games;
    }

}
