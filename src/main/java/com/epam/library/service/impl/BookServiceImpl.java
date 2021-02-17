package com.epam.library.service.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Book;
import com.epam.library.service.CommonService;

import java.util.List;

public class BookServiceImpl implements CommonService<Book> {

    private final CommonDao<Book> bookDao;

    public BookServiceImpl(CommonDao<Book> bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
