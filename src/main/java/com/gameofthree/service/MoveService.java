package com.gameofthree.service;

import com.gameofthree.model.Move;
import com.gameofthree.model.Player;

public interface MoveService {
  Move processMove(Move lastMove, Integer operation, Player player);

}
