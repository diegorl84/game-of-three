package com.gameofthree.exception;

public class InvalidMoveException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public InvalidMoveException() {
    super();
  }

  public InvalidMoveException(String message) {
    super(message);
  }

}
