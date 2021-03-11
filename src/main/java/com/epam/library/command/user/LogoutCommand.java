package com.epam.library.command.user;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.command.page.ShowSignInPageCommand;

public enum LogoutCommand implements Command {

    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext request) {
        request.invalidateSession();
        return ShowSignInPageCommand.INSTANCE.execute(request);
    }
}
