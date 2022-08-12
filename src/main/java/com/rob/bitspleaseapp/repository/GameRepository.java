package com.rob.bitspleaseapp.repository;

import com.rob.bitspleaseapp.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Iterable<Game> findByNameContains(String name);

    Iterable<Game> findBySystem(String system);

    Iterable<Game> findBySystemAndNameContains(String system, String name);

    Iterable<Game> findAllByUploader(long uploader);
}
