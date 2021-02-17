package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Book;
import com.epam.library.pool.ConnectionPool;
import com.epam.library.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class BookDaoImpl implements CommonDao<Book> {

    private static final String FIND_ALL_BOOKS_SQL = "SELECT id, title, year_of_publishing FROM book";
    private static final String ID_COLUMN_NAME = "id";
    private static final String BOOK_NAME_COLUMN_NAME = "title";
    private static final String YEAR_OF_PUBLISHING_COLUMN_NAME = "year_of_publishing";


    private final DatabaseConnector databaseConnector = new DatabaseConnector();
    private PreparedStatement preparedStatement;

    @Override
    public List<Book> findAll() {

        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from book;");
             ResultSet resultSet = statement.executeQuery()) {
            Book book;
            while (resultSet.next()) {
                book = new Book(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
                books.add(book);
            }
        } catch (SQLException exception) {
            // TODO: logger
        }


//        try {
//            ConnectionPool.INSTANCE.init();
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//        }
//        List<Book> books = new ArrayList<>();
//        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
//             final Statement statement = connection.createStatement();
//             final ResultSet resultSet = statement.executeQuery(FIND_ALL_BOOKS_SQL)) {
//            while (resultSet.next()) {
//                books.add(readBook(resultSet));
//            }
//
//        }catch (SQLException exception){
//            // TODO: logger
//        }

//        ResultSet resultSet;
//        Book book;
//        databaseConnector.startConnection();
//        try {
//            preparedStatement = DatabaseConnector.getConnection().prepareStatement("select * from book;");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                book = new Book(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
//                books.add(book);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            databaseConnector.closeConnection();
//        }
//
        return books;
    }

    private Book readBook(ResultSet resultSet) throws SQLException {
        return new Book(resultSet.getLong(ID_COLUMN_NAME),
                resultSet.getString(BOOK_NAME_COLUMN_NAME),
                resultSet.getInt(YEAR_OF_PUBLISHING_COLUMN_NAME));
    }
}
