package com.epam.library.dao;

import java.util.List;

public interface CommonDao<T> {
    List<T> findAll();
}
