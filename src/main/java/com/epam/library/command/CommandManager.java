package com.epam.library.command;

import com.epam.library.command.page.ShowAllBooksCommand;
import com.epam.library.command.page.ShowMainPageCommand;

public enum CommandManager {

    DEFAULT(ShowMainPageCommand.INSTANCE),
    SHOW_BOOKS(ShowAllBooksCommand.INSTANCE);


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
        return DEFAULT.command;
    }

}
