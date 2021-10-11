package com.gameofthree.repository;

import java.util.concurrent.ConcurrentMap;

public abstract class Repository<E> implements GameRepository<E>  {
  protected ConcurrentMap<String, E> repository;
}
