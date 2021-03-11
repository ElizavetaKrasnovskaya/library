package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Role;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements CommonDao<Role> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from role;");
             ResultSet resultSet = statement.executeQuery()) {
            Role role;
            while (resultSet.next()) {
                role = new Role(resultSet.getLong(1), resultSet.getString(2));
                roles.add(role);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return roles;
    }

    @Override
    public Role findById(Long id) {
        Role role = new Role();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from role where id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong(1));
                role.setType(resultSet.getString(2));
            }
        } catch (SQLException exception) {
            role = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return role;
    }

    @Override
    public Role findByParameter(String type) {
        Role role = new Role();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from role where type = ?;");
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong(1));
                role.setType(resultSet.getString(2));
            }
        } catch (SQLException exception) {
            role = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return role;
    }

    @Override
    public boolean create(Role role) {
        return false;
    }
}
