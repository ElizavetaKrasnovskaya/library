package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Author;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements CommonDao<Author> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoImpl.class);

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from author;");
             ResultSet resultSet = statement.executeQuery()) {
            Author author;
            while (resultSet.next()) {
                author = new Author(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3));
                authors.add(author);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return authors;
    }

    @Override
    public Author findById(Long id) {
        Author author = new Author();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){

            PreparedStatement statement = connection.prepareStatement("select * from author where id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                author.setId(id);
                author.setSurname(resultSet.getString(2));
                author.setFirstName(resultSet.getString(3));
            }
        } catch (SQLException exception) {
            author = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return author;
    }

    @Override
    public Author findByParameter(String name) {
        Author author = new Author();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){

            PreparedStatement statement = connection.prepareStatement("select * from author where id = ?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                author.setSurname(resultSet.getString(2));
                author.setFirstName(resultSet.getString(3));
            }
        } catch (SQLException exception) {
            author = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return author;
    }

    @Override
    public boolean create(Author author){
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into author " +
                    "(surname, first_name) values (?,?);");
            statement.setString(1, author.getSurname());
            statement.setString(2, author.getFirstName());
            statement.executeUpdate();
            return true;
        }catch (SQLException exception){
            LOGGER.info("SQLException: {}.", exception.getMessage());
            return false;
        }
    }
}
