package bitspleaseApp.controller;

import bitspleaseApp.model.Game;
import bitspleaseApp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "games")
public class  GameController {

    private GameService gameService;

    @Autowired
    public
    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity getGames(@RequestParam(value = "name", required = false) String name) {
        Iterable<Game> games;
        if (name == null) {
            games = gameService.findAll();
        } else {
            games = gameService.findByName(name);
        }
                return ResponseEntity.ok(games);
    }


    @GetMapping(value = "/id/{id}")
    public ResponseEntity findGamesById(@PathVariable long id) {
        Optional<Game> game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping(value = "/system/{system}")
    public ResponseEntity findGamesBySystem(@PathVariable String system) {
        Iterable<Game> games = gameService.findBySystem(system);
        return ResponseEntity.ok(games);
    }


    @PostMapping
    public ResponseEntity addGame(@RequestBody Game game) {
        gameService.save(game);
        return ResponseEntity.ok(game);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGame(@PathVariable long id) {
        gameService.deleteById(id);
        return ResponseEntity.ok("Verwijderd!");
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity updateGame(@PathVariable long id, @RequestBody Game game) {
        gameService.updateGame(id, game);
        return ResponseEntity.ok("game aangepast.");
    }


}
