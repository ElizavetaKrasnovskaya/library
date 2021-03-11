package com.epam.library.dao.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.model.Book;
import com.epam.library.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements CommonDao<Book> {

    private static final String FIND_ALL_BOOKS_SQL = "SELECT id, title, year_of_publishing FROM book";
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Book book;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from book;");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setGenre(resultSet.getString(3));
                book.setYearOfPublishing(resultSet.getInt(4));
                book.setInReadingRoom(resultSet.getBoolean(5));
                book.setTaken(resultSet.getBoolean(6));
                book.setSpecification(resultSet.getString(7));
                book.setAuthor(new AuthorDaoImpl().findById(resultSet.getLong(9)));
                books.add(book);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return books;
    }

    @Override
    public Book findById(Long id) {
        Book book = new Book();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from book where id = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setGenre(resultSet.getString(3));
                book.setYearOfPublishing(resultSet.getInt(4));
                book.setInReadingRoom(resultSet.getBoolean(5));
                book.setTaken(resultSet.getBoolean(6));
                book.setSpecification(resultSet.getString(7));
                book.setState(new StateDaoImpl().findById(resultSet.getLong(8)));
                book.setAuthor(new AuthorDaoImpl().findById(resultSet.getLong(9)));
            }
        } catch (SQLException exception) {
            book = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return book;
    }

    public List<Book> findByReaderTicket(Long readerTicketId) {
        List<Book> books = new ArrayList<>();
        Book book;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from book where reader_ticket_id = ?;");
            statement.setLong(1, readerTicketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setGenre(resultSet.getString(3));
                book.setYearOfPublishing(resultSet.getInt(4));
                book.setInReadingRoom(resultSet.getBoolean(5));
                book.setTaken(resultSet.getBoolean(6));
                book.setSpecification(resultSet.getString(7));
                book.setState(new StateDaoImpl().findById(resultSet.getLong(8)));
                book.setAuthor(new AuthorDaoImpl().findById(resultSet.getLong(9)));
                books.add(book);
            }
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return books;
    }

    @Override
    public Book findByParameter(String title) {
        Book book = new Book();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from book where id = ?;");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setGenre(resultSet.getString(3));
                book.setYearOfPublishing(resultSet.getInt(4));
                book.setInReadingRoom(resultSet.getBoolean(5));
                book.setTaken(resultSet.getBoolean(6));
                book.setSpecification(resultSet.getString(7));
                book.setState(new StateDaoImpl().findById(resultSet.getLong(8)));
                book.setAuthor(new AuthorDaoImpl().findById(resultSet.getLong(9)));
            }
        } catch (SQLException exception) {
            book = null;
            LOGGER.info("SQLException: {}.", exception.getMessage());
        }
        return book;
    }

    @Override
    public boolean create(Book book) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into book " +
                    "(title, genre, year_of_publishing, in_reading_room, is_taken, specification) values (?,?,?,?,?,?);");
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getGenre());
            statement.setInt(3, book.getYearOfPublishing());
            statement.setBoolean(4, book.isInReadingRoom());
            statement.setBoolean(5, book.isTaken());
            statement.setString(6, book.getSpecification());
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
            return false;
        }
    }

    public boolean updateReaderTicket(Long bookId, Long readerTicketId) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update book set " +
                    "reader_ticket_id=?, is_taken = ?, state_id=? where id=?;");
            statement.setLong(1, readerTicketId);
            statement.setLong(2, 1);
            statement.setBoolean(3, true);
            statement.setLong(4, bookId);
            statement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}.", exception.getMessage());
            return false;
        }
    }
}
