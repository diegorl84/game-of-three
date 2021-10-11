package com.gameofthree.dto;

public class JoinGameRequest {

  private String name;
  private String gameId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}
