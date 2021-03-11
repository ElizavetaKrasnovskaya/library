package com.epam.library.service.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.service.CommonService;

import java.util.List;

public class BookServiceImpl implements CommonService<Book> {

    private final BookDaoImpl bookDao;

    public BookServiceImpl(BookDaoImpl bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    public boolean updateReaderTicket(Long bookId, Long readerTicketId){
        return bookDao.updateReaderTicket(bookId, readerTicketId);
    }

    public List<Book> findByReaderTicket(Long readerTicketId) {
        return bookDao.findByReaderTicket(readerTicketId);
    }
}
