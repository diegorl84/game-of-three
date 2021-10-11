package com.gameofthree.service;

import com.gameofthree.dto.CreateGameRequest;
import com.gameofthree.model.Game;
import com.gameofthree.model.Player;
import java.util.List;

public interface GameService {
  Game createGame(CreateGameRequest request);
  Game joinGame(Player player, String gameId);
  Game joinGame(Player player);
  Game move(String gameId, Integer operation, Player currentPlayer);
  List<Game> listGames();

}
