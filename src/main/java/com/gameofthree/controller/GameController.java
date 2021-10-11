package com.gameofthree.controller;

import com.gameofthree.dto.CreateGameRequest;
import com.gameofthree.dto.JoinGameRequest;
import com.gameofthree.dto.PlayerRequest;
import com.gameofthree.dto.MoveRequest;
import com.gameofthree.model.Game;
import com.gameofthree.model.Player;
import com.gameofthree.service.GameServiceImpl;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-of-three")
public class GameController {

  private final GameServiceImpl gameService;
  private final SimpMessagingTemplate simpMessagingTemplate;

  public GameController(GameServiceImpl gameService, SimpMessagingTemplate simpMessagingTemplate) {
    this.gameService = gameService;
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @PostMapping("/game")
  public ResponseEntity<Game> createGame(@RequestBody CreateGameRequest request) {
      final Game game = gameService.createGame(request);
      return ResponseEntity.ok(game);
  }

  @PostMapping("/join-random-game")
  public ResponseEntity<Game> joinGameRandomly(@RequestBody PlayerRequest playerRequest) {
    final Player player = new Player(playerRequest.getName(), false);
    final Game game = gameService.joinGame(player);
    return ResponseEntity.ok(game);
  }

  @PostMapping("/join-game")
  public ResponseEntity<Game> joinGameById(@RequestBody JoinGameRequest joinRequest) {
    final Player player = new Player(joinRequest.getName(), false);
    final Game game = gameService.joinGame(player, joinRequest.getGameId());
    return ResponseEntity.ok(game);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Game>> listGames() {
    return ResponseEntity.ok(gameService.listGames());
  }

  @PostMapping("/move")
  public ResponseEntity<Game> move(@RequestBody MoveRequest moveRequest) {
    final Game game =
        gameService.move(
            moveRequest.getGameId(), moveRequest.getOperation(), moveRequest.getPlayer());
    simpMessagingTemplate.convertAndSend("/topic/game/" + game.getId(), game);
    return ResponseEntity.ok(game);
  }


}
