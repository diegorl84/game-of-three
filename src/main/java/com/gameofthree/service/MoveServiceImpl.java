package com.gameofthree.service;

import com.gameofthree.model.Move;
import com.gameofthree.model.Player;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

@Service
public class MoveServiceImpl implements MoveService {

  private static final Integer DIVISOR = 3;

  @Override
  public Move processMove(Move lastMove, Integer operation, Player player) {
    return new Move(player, operation, (lastMove.getResultNumber() + operation) / DIVISOR, LocalDateTime.now().toEpochSecond(
        ZoneOffset.UTC));
  }
}
