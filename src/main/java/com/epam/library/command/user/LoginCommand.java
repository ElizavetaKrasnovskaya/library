package com.epam.library.command.user;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.command.page.ShowMainPageCommand;
import com.epam.library.command.page.ShowSignInPageCommand;
import com.epam.library.dao.impl.UserDaoImpl;
import com.epam.library.model.User;
import com.epam.library.service.impl.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

public enum LoginCommand implements Command {

    INSTANCE;

    private final UserServiceImpl userService;

    LoginCommand(){
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final String name = String.valueOf(request.getParameter("login"));
        final String password = String.valueOf(request.getParameter("password"));
        final User user = userService.findByParameters(name);
        ResponseContext result;
        if (user.getLogin().equals(name) && BCrypt.checkpw(password, user.getPassword())) {
            request.setSessionAttribute("user", user);
            result = ShowMainPageCommand.INSTANCE.execute(request);
        } else {
            request.setAttribute("errorMessage", "invalid credentials");
            result = ShowSignInPageCommand.INSTANCE.execute(request);
        }
        return result;
    }
}
