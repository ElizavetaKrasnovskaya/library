package com.epam.library.service;

import com.epam.library.model.Book;

import java.util.List;

public interface CommonService <T>{
    List<T> findAll();
    Book findById(Long id);
}
