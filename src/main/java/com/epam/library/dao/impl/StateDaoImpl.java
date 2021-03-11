package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.State;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateDaoImpl implements CommonDao<State> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateDaoImpl.class);

    @Override
    public List<State> findAll() {
        List<State> states = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from state_of_book;");
             ResultSet resultSet = statement.executeQuery()) {
            State state;
            while (resultSet.next()) {
                state = new State();
                state.setId(resultSet.getLong(1));
                state.setType(resultSet.getString(2));
                states.add(state);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return states;
    }

    @Override
    public State findById(Long id) {
        return null;
    }

    @Override
    public State findByParameter(String parameter) {
        return null;
    }

    @Override
    public boolean create(State state) {
        return false;
    }
}
