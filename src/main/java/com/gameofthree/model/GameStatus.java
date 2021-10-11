package com.gameofthree.model;

public enum GameStatus {
  IDLE("idle"),
  IN_PROGRESS("in_progress"),
  FINISHED("finished");

  private final String value;

  GameStatus(final String value){
    this.value = value;
  }


}
