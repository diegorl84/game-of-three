package com.gameofthree.exception;

public class InvalidGameException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidGameException() {
    super();
  }

  public InvalidGameException(String message) {
    super(message);
  }
}
