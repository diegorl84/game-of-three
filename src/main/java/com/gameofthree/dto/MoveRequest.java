package com.gameofthree.dto;

import com.gameofthree.model.Player;

public class MoveRequest {

  private String gameId;
  private Integer operation;
  private Player player;

  public MoveRequest(String gameId, Integer operation, Player player) {
    this.gameId = gameId;
    this.operation = operation;
    this.player = player;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public Integer getOperation() {
    return operation;
  }

  public void setOperation(Integer operation) {
    this.operation = operation;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
