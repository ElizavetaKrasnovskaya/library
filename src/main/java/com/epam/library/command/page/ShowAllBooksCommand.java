package com.epam.library.command.page;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.service.CommonService;
import com.epam.library.service.impl.BookServiceImpl;

import java.util.Collections;
import java.util.List;

public enum ShowAllBooksCommand implements Command {

    INSTANCE;

    private static final String BOOKS_ATTRIBUTE_NAME = "books";

    private final CommonService<Book> bookService;

    ShowAllBooksCommand() {
        bookService = new BookServiceImpl(new BookDaoImpl());
    }


    private static final ResponseContext BOOKS_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/books.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        final List<Book> books = bookService.findAll();
        request.setAttribute(BOOKS_ATTRIBUTE_NAME, books);
        return BOOKS_PAGE_RESPONSE;
    }
}
