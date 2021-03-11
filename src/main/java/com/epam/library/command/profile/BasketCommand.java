package com.epam.library.command.profile;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.command.page.ShowBookByIdCommand;
import com.epam.library.command.page.ShowMainPageCommand;
import com.epam.library.command.page.ShowProfileCommand;
import com.epam.library.command.page.ShowSignInPageCommand;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;

public enum BasketCommand implements Command {

    INSTANCE;

    private final UserServiceImpl userService;

    BasketCommand(){
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final Long id = Long.parseLong((String) request.getParameter("id"));
        final User user = (User) request.getSession().getAttribute("user");
        final BookServiceImpl bookService = new BookServiceImpl(new BookDaoImpl());
        boolean update = bookService.updateReaderTicket(id, user.getId());
        ResponseContext result;
        if (update) {
            result = ShowProfileCommand.INSTANCE.execute(request);
        } else {
            request.setAttribute("errorMessage", "invalid credentials");
            result = ShowBookByIdCommand.INSTANCE.execute(request);
        }
        return result;
    }
}
