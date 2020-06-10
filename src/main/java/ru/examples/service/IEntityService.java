package ru.examples.service;

import ru.examples.annotation.Loggable;

import java.util.List;

public interface IEntityService<T> {

    List<T> findAll();

    T findById(Object id);

    T create(T object);

    T update(T object);

    void delete(Object id);
}
