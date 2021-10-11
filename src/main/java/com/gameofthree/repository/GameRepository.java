package com.gameofthree.repository;

import java.util.Map;
import java.util.Optional;

public interface GameRepository<E> {

  E save(E e);

  Map<String, E> list();

  Optional<E> getById(String id);

  Optional<E> getFirstIdle();

}
