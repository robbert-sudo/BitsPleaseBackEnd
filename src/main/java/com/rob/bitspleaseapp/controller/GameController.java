package com.rob.bitspleaseapp.controller;

import com.rob.bitspleaseapp.model.Game;
import com.rob.bitspleaseapp.dto.request.GamePatchRequest;
import com.rob.bitspleaseapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }



    @PostMapping
    public ResponseEntity addGame(@RequestBody Game game) {
        Optional<Game> savedGame = gameService.save(game);
        return ResponseEntity.ok(savedGame);
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
    public ResponseEntity findGameById(@PathVariable long id) {
        Optional<Game> game = gameService.findById(id);
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
        return ResponseEntity.ok(game);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity patchGame(@PathVariable long id, @RequestBody GamePatchRequest gamePatchRequest) {
        Optional<Game> patchedGame = gameService.patchGame(id, gamePatchRequest);
        return ResponseEntity.ok(patchedGame);
    }

    @GetMapping(value = "/systemandname/{system}")
    public ResponseEntity findBySystemAndName(@PathVariable String system, @RequestParam(value = "name", required = true) String name) {
        Iterable<Game> games = gameService.findBySystemAndNameContains(system, name);
        return ResponseEntity.ok(games);
    }

    @GetMapping(value = "/uploader/{uploader}")
    public ResponseEntity findByUploaderId(@PathVariable long uploader) {
        Iterable<Game> games = gameService.findAllByUploader(uploader);
        return ResponseEntity.ok(games);
    }
}
