package com.epam.library.command.page;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;

public enum ShowSignInPageCommand implements Command {

    INSTANCE;

    private static final ResponseContext SIGN_IN_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/sign_in.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        return SIGN_IN_PAGE;
    }
}
