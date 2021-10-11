package com.gameofthree.dto;

public class ListGameRequest {
  private String status;

  public ListGameRequest() {
  }

  public ListGameRequest(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
