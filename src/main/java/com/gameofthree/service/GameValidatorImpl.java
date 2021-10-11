package com.gameofthree.service;

import com.gameofthree.model.Game;
import com.gameofthree.model.GameStatus;
import com.gameofthree.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameValidatorImpl implements GameValidator {

  @Override
  public void validateGameToPlay(Game game, Player player) {
    if (game.getStatus() != GameStatus.IN_PROGRESS) {
      throw new IllegalStateException("Status of the game is not allowed for playing");
    }
    if (!game.getNextPlayer().getName().equals(player.getName())) {
      throw new IllegalStateException("Player " + player.getName() +  " is not correct for next-player to play");
    }
  }
}
