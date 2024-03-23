package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface DaoOperation<T, I> {
    List<T> findAll();

    Optional<T> findById(I id);

    void save(T t);

    void update(T t);

    void deletedById(I i);
}