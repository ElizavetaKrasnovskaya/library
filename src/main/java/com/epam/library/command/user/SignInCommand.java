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

public enum SignInCommand implements Command {

    INSTANCE;

    private final UserServiceImpl userService;

    SignInCommand(){
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    public ResponseContext execute(RequestContext request) {

        final String surname = String.valueOf(request.getParameter("surname"));
        final String firstName = String.valueOf(request.getParameter("firstName"));
        final String email = String.valueOf(request.getParameter("email"));
        final String login = String.valueOf(request.getParameter("login"));
        final String password = BCrypt.hashpw(String.valueOf(request.getParameter("password")), BCrypt.gensalt());
        ResponseContext result;
        if(userService.createUser(surname, firstName, email, login, password)){
            User user = userService.findByParameters(login);
            if (user.getLogin().equals(login) && BCrypt.checkpw(String.valueOf(request.getParameter("password")), user.getPassword())) {
                request.setSessionAttribute("user", user);
                result = ShowMainPageCommand.INSTANCE.execute(request);
            } else {
                request.setAttribute("errorMessage", "invalid credentials");
                result = ShowSignInPageCommand.INSTANCE.execute(request);
            }
        } else {
            request.setAttribute("errorMessage", "invalid credentials");
            result = ShowSignInPageCommand.INSTANCE.execute(request);
        }
        return result;
    }

}
