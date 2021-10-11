package com.gameofthree.dto;

public class CreateGameRequest {
  private String name;
  private Integer number;
  private boolean playAuto;

  public CreateGameRequest(String name, Integer number, boolean playRandom) {
    this.name = name;
    this.number = number;
    this.playAuto = playRandom;
  }

  public boolean isPlayAuto() {
    return playAuto;
  }

  public void setPlayAuto(boolean playAuto) {
    this.playAuto = playAuto;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
}
