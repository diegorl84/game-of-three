package com.gameofthree.service;

import com.gameofthree.dto.CreateGameRequest;
import com.gameofthree.exception.GameNotFoundException;
import com.gameofthree.exception.InvalidGameException;
import com.gameofthree.exception.InvalidNumberException;
import com.gameofthree.model.Game;
import com.gameofthree.model.GameStatus;
import com.gameofthree.model.Move;
import com.gameofthree.model.Player;
import com.gameofthree.repository.GameRepositoryImpl;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{

  private static final Integer RESULT_TO_WIN = 1;
  private static final Integer RESULT_TO_LOSE = 0;

  private final GameRepositoryImpl storageService;
  private final GameValidatorImpl turnValidator;
  private final MoveServiceImpl calculationService;

  public GameServiceImpl(
      GameRepositoryImpl storageService,
      GameValidatorImpl turnValidator,
      MoveServiceImpl calculationService) {
    this.storageService = storageService;
    this.turnValidator = turnValidator;
    this.calculationService = calculationService;
  }

  @Override
  public Game createGame(CreateGameRequest request) {
    if(request.getNumber() < 2 ){
      throw new InvalidNumberException("Number cannot be one or lower");
    }

    final Game game = new Game();
    final Player player = new Player(request.getName(), request.isPlayAuto());
    game.setPlayer1(player);
    game.setStatus(GameStatus.IDLE);
    game.getHistoryOfMove().add(createMove(player, 0, request.getNumber()));
    storageService.save(game);
    return game;
  }

  @Override
  public Game joinGame(Player player, String gameId) {
    final Game game =
        storageService
            .getById(gameId)
            .orElseThrow(() -> new GameNotFoundException("Game ID not found"));

    game.setPlayer2(player);
    game.setStatus(GameStatus.IN_PROGRESS);
    game.setNextPlayer(player);
    storageService.save(game);
    return game;
  }

  @Override
  public Game joinGame(Player player) {
    final Game game =
        storageService
            .getFirstIdle()
            .orElseThrow(() -> new GameNotFoundException("No game with IDLE status found"));

    game.setPlayer2(player);
    game.setStatus(GameStatus.IN_PROGRESS);
    game.setNextPlayer(player);
    storageService.save(game);
    return game;
  }

  @Override
  public Game move(String gameId, Integer operation, Player currentPlayer) {
    final Game game =
        storageService
            .getById(gameId)
            .orElseThrow(() -> new InvalidGameException("Game ID not found"));

    turnValidator.validateGameToPlay(game, currentPlayer);

    final Move move =
        calculationService.processMove(game.getHistoryOfMove().getLast(), operation, currentPlayer);

    game.getHistoryOfMove().add(move);

    if (move.getResultNumber() == RESULT_TO_WIN) {
      game.setStatus(GameStatus.FINISHED);
      game.setWinner(currentPlayer);
    } else if (move.getResultNumber() == RESULT_TO_LOSE) {
      game.setStatus(GameStatus.FINISHED);
      game.setWinner(getNextPlayer(currentPlayer, game));
    }
    game.setNextPlayer(getNextPlayer(currentPlayer, game));

    storageService.save(game);
    return game;
  }

  @Override
  public List<Game> listGames() {
    return storageService.list().values().stream().collect(Collectors.toList());
  }

  private Player getNextPlayer(Player currentPlayer, Game game) {
    return currentPlayer.getName().equals(game.getPlayer1().getName())
        ? game.getPlayer2()
        : game.getPlayer1();
  }

  private Move createMove(Player player, Integer added, Integer result) {
    return new Move(player, added, result, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
  }
}
