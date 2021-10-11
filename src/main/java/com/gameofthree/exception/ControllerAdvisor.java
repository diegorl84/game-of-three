package com.gameofthree.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidNumberException.class)
  public ResponseEntity<Object> handleInvalidNumberException(
      InvalidNumberException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(GameNotFoundException.class)
  public ResponseEntity<Object> handleGameNotFoundException(
      GameNotFoundException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidGameException.class)
  public ResponseEntity<Object> handleInvalidGameException(
      InvalidGameException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<Object> handleInvalidStateException(
      IllegalStateException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidMoveException.class)
  public ResponseEntity<Object> handleInvalidMoveException(
      InvalidMoveException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus httpStatus) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", message);

    return new ResponseEntity<>(body, httpStatus);
  }
}
