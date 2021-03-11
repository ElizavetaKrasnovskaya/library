package com.epam.library.dao;

import java.util.List;

public interface CommonDao<T> {
    List<T> findAll();
    T findById(Long id);
    T findByParameter(String parameter);
    boolean create(T t);
}
