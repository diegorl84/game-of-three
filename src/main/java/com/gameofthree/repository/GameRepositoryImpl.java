package com.gameofthree.repository;

import com.gameofthree.model.Game;
import com.gameofthree.model.GameStatus;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

@Service
public class GameRepositoryImpl extends Repository<Game> {

  public GameRepositoryImpl() {
    super.repository = new ConcurrentHashMap<>();
  }

  @Override
  public Game save(Game game) {
    return repository.put(game.getId(), game);
  }

  @Override
  public ConcurrentMap<String, Game> list() {
    return repository;
  }

  @Override
  public Optional<Game> getById(String id) {
    return Optional.ofNullable(repository.get(id));
  }

  @Override
  public Optional<Game> getFirstIdle() {
    return repository.values().stream().filter(g -> g.getStatus() == GameStatus.IDLE).findFirst();
  }
}
