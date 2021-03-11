package com.epam.library.command;

import com.epam.library.command.page.ShowAllBooksCommand;
import com.epam.library.command.page.ShowBookByIdCommand;
import com.epam.library.command.page.ShowProfileCommand;
import com.epam.library.command.page.ShowSignInPageCommand;
import com.epam.library.command.page.ShowMainPageCommand;
import com.epam.library.command.page.ShowSignUpPageCommand;
import com.epam.library.command.profile.BasketCommand;
import com.epam.library.command.user.LoginCommand;
import com.epam.library.command.user.LogoutCommand;
import com.epam.library.command.user.SignInCommand;

public enum CommandManager {

    MAIN_PAGE(ShowMainPageCommand.INSTANCE),
    BOOKS(ShowAllBooksCommand.INSTANCE),
    SIGN_IN(ShowSignInPageCommand.INSTANCE),
    SIGN_UP(ShowSignUpPageCommand.INSTANCE),
    LOGIN(LoginCommand.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
    NEW_USER(SignInCommand.INSTANCE),
    PROFILE(ShowProfileCommand.INSTANCE),
    BASKET(BasketCommand.INSTANCE),
    BOOK(ShowBookByIdCommand.INSTANCE);


    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String name) {
        for (CommandManager action : values()) {
            if (action.name().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return MAIN_PAGE.command;
    }

}
