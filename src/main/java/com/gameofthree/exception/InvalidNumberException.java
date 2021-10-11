package com.gameofthree.exception;

public class InvalidNumberException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public InvalidNumberException() {
    super();
  }

  public InvalidNumberException(String message) {
    super(message);
  }

}
