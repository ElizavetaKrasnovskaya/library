package com.epam.library.command.page;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.model.User;
import com.epam.library.service.CommonService;
import com.epam.library.service.impl.BookServiceImpl;

import java.util.List;

public enum ShowProfileCommand implements Command {

    INSTANCE;

    private static final String BOOKS_ATTRIBUTE_NAME = "books";

    private final BookServiceImpl bookService;

    ShowProfileCommand(){
        bookService = new BookServiceImpl(new BookDaoImpl());
    }

    private static final ResponseContext PROFILE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/profile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        final User user = (User) request.getSession().getAttribute("user");
        final List<Book> books = bookService.findByReaderTicket(user.getId());
        request.setAttribute(BOOKS_ATTRIBUTE_NAME, books);
        return PROFILE;
    }
}
