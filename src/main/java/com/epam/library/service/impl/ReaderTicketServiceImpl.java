package com.epam.library.service.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Book;
import com.epam.library.model.ReaderTicket;
import com.epam.library.model.User;
import com.epam.library.service.CommonService;

import java.time.LocalDate;
import java.util.List;

public class ReaderTicketServiceImpl implements CommonService {

    private final CommonDao<ReaderTicket> readerTicketCommonDao;

    public ReaderTicketServiceImpl(CommonDao<ReaderTicket> readerTicketCommonDao) {
        this.readerTicketCommonDao = readerTicketCommonDao;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    public boolean create(User user){
        ReaderTicket readerTicket = new ReaderTicket();
        readerTicket.setReader(user);
        readerTicket.setDate(LocalDate.now());
        return readerTicketCommonDao.create(readerTicket);
    }
}
