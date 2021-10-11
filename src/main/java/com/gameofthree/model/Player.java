package com.gameofthree.model;

public class Player {
  private String name;
  private boolean playRandom;

  public Player() {
  }

  public Player(String name, boolean playRandom) {
    this.name = name;
    this.playRandom = playRandom;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPlayRandom() {
    return playRandom;
  }

  public void setPlayRandom(boolean playRandom) {
    this.playRandom = playRandom;
  }
}
