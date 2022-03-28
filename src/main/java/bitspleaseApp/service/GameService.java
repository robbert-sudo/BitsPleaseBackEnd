package bitspleaseApp.service;

import bitspleaseApp.model.Game;

import java.util.Optional;

public interface GameService {


    void save(Game game);

    Iterable<Game> findAll();

    Iterable<Game> findByName(String name);

    void deleteById(long id);

    void updateGame(long id, Game game);

    Iterable<Game> findBySystem(String system);

    Optional<Game> findById(long id);
}
