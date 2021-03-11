package com.epam.library.command.page;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;

public enum ShowSignUpPageCommand implements Command {
    INSTANCE;

    private static final ResponseContext SIGN_UP_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/sign_up.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        return SIGN_UP_PAGE;
    }
}
