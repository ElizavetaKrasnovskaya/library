package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.ReaderTicket;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReaderTicketDaoImpl implements CommonDao<ReaderTicket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderTicketDaoImpl.class);


    @Override
    public List<ReaderTicket> findAll() {
        return null;
    }

    @Override
    public ReaderTicket findById(Long id) {
        ReaderTicket readerTicket = new ReaderTicket();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from reader_ticket where reader_id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                readerTicket.setId(resultSet.getLong(1));
                readerTicket.setReader(new UserDaoImpl().findById(resultSet.getLong(2)));
            }
        } catch (SQLException exception) {
            readerTicket = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return readerTicket;
    }

    @Override
    public ReaderTicket findByParameter(String parameter) {
        return null;
    }

    @Override
    public boolean create(ReaderTicket readerTicket) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into reader_ticket " +
                    "(reader_id, start_of_ticket) values (?,?);");
            statement.setLong(1, readerTicket.getReader().getId());
            statement.setObject(2, readerTicket.getDate());
            statement.executeUpdate();
            return true;
        }catch (SQLException exception){
            LOGGER.info("SQLException: {}.", exception.getMessage());
            return false;
        }
    }
}
