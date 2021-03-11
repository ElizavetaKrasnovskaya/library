package com.epam.library.service.impl;

import com.epam.library.dao.CommonDao;
import com.epam.library.dao.impl.ReaderTicketDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.service.CommonService;

import java.util.List;

public class UserServiceImpl implements CommonService<User> {

    private final CommonDao<User> userDao;

    public UserServiceImpl(CommonDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    public User findByParameters(String login) {
        return userDao.findByParameter(login);
    }

    public boolean createUser(String surname, String firstName, String email, String login, String password) {
        User user = new User();
        user.setSurname(surname);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        return new UserDaoImpl().create(user);
    }
}
