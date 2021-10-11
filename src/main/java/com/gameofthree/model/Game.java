package com.gameofthree.model;

import com.gameofthree.exception.InvalidGameException;
import com.gameofthree.exception.InvalidNumberException;
import java.util.LinkedList;
import java.util.UUID;

public class Game {
  private String id;
  private Player player1;
  private Player player2;
  private GameStatus status;
  private LinkedList<Move> historyOfMove;
  private Player nextPlayer;
  private Player winner;

  public Game() {
    this.id = UUID.randomUUID().toString();
    this.historyOfMove = new LinkedList<>();
  }

  public String getId() {
    return id;
  }

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    if(player1.getName().isEmpty()){
      throw new InvalidGameException("Player one must not be empty");
    }
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    if(player2.getName().isEmpty()){
      throw new InvalidGameException("Player two must not be empty");
    }
    if(this.status != GameStatus.IDLE ){
      throw new InvalidGameException("Game with ID:" + this.id + "is in progress. No more players allowed");
    }
    if(this.getPlayer1().getName().equals(player2.getName())){
      throw new IllegalStateException("Player two must not be the same as player one");
    }
    this.player2 = player2;
  }

  public GameStatus getStatus() {
    return status;
  }

  public void setStatus(GameStatus status) {
    if((status == GameStatus.IN_PROGRESS || status == GameStatus.FINISHED)  && (this.player1 == null || this.player2 == null)){
      throw new IllegalStateException("Status invalid. Both players are required");
    }

    if(status == GameStatus.IDLE && this.player1 == null ){
      throw new IllegalStateException("Status invalid. IDLE requires at least player one");
    }

    this.status = status;
  }

  public LinkedList<Move> getHistoryOfMove() {
    return historyOfMove;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {

    this.winner = winner;
  }

  public Player getNextPlayer() {
    return nextPlayer;
  }

  public void setNextPlayer(Player nextPlayer) {
    this.nextPlayer = nextPlayer;
  }
}
