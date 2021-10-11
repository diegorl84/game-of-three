package com.gameofthree.service;

import com.gameofthree.model.Game;
import com.gameofthree.model.Player;

public interface GameValidator {
  void validateGameToPlay(Game game, Player player);

}
