package com.gameofthree.dto;

public class PlayerRequest {
  private String name;

  public PlayerRequest(){ }

  public PlayerRequest(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
