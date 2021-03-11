package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.ReaderTicket;
import com.epam.library.model.User;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements CommonDao<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from user;");
             ResultSet resultSet = statement.executeQuery()) {
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setSurname(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setLogin(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setRole(new RoleDaoImpl().findById(resultSet.getLong(8)));
                users.add(user);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getSQLState());
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from user where id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setSurname(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setLogin(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setRole(new RoleDaoImpl().findById(resultSet.getLong(8)));
            }
        } catch (SQLException exception) {
            user = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return user;
    }

    @Override
    public User findByParameter(String login) {
        User user = new User();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            PreparedStatement statement = connection.prepareStatement("select * from user where login = ?;");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong(1));
                user.setSurname(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setLogin(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setRole(new RoleDaoImpl().findById(resultSet.getLong(8)));
            }
        } catch (SQLException exception) {
            user = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return user;
    }

    public boolean create(User user){
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into user " +
                    "(surname, first_name, email, login, password, role_id) values (?,?,?,?,?,?);");
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setLong(6, 1);
            statement.executeUpdate();
            update(user);
            return true;
        }catch (SQLException exception){
            LOGGER.info("SQLException: {}.", exception.getMessage());
            return false;
        }
    }

    public void update(User user){
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update user set reader_ticket_id=? where id=?;");
            Long id = findByParameter(user.getLogin()).getId();
            ReaderTicketDaoImpl readerTicketDao = new ReaderTicketDaoImpl();
            ReaderTicket readerTicket = new ReaderTicket();
            readerTicket.setId(id);
            readerTicket.setReader(findById(id));
            readerTicket.setDate(LocalDate.now());
            readerTicketDao.create(readerTicket);
            statement.setLong(2, id);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException exception){
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
    }
}
