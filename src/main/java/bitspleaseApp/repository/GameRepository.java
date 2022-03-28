package bitspleaseApp.repository;

import bitspleaseApp.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository <Game, Long> {

    Iterable<Game> findByNameContains(String name);

    Iterable<Game> findBySystem(String system);
}
