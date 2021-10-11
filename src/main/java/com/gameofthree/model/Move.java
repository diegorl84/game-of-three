package com.gameofthree.model;

import com.gameofthree.exception.InvalidMoveException;

public class Move {
  private Player player;
  private Integer addedNumber;
  private Integer resultNumber;
  private Long date;

  public Move(Player player, Integer addedNumber, Integer resultNumber, Long date) {
    this.player = player;
    if (addedNumber < -1 || addedNumber > 1) {
      throw new InvalidMoveException("Operation invalid. Number added must be -1, 0 or 1");
    }
    this.addedNumber = addedNumber;
    this.resultNumber = resultNumber;
    this.date = date;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Integer getResultNumber() {
    return resultNumber;
  }

  public Integer getAddedNumber() {
    return addedNumber;
  }

  public Long getDate() {
    return date;
  }
}
