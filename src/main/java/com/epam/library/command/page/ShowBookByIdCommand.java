package com.epam.library.command.page;

import com.epam.library.command.Command;
import com.epam.library.command.RequestContext;
import com.epam.library.command.ResponseContext;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.model.Book;
import com.epam.library.service.CommonService;
import com.epam.library.service.impl.BookServiceImpl;

public enum ShowBookByIdCommand implements Command {
    INSTANCE;
    private static final String BOOK_ATTRIBUTE_NAME = "book";

    private final CommonService<Book> bookService;

    ShowBookByIdCommand() {
        bookService = new BookServiceImpl(new BookDaoImpl());
    }


    private static final ResponseContext BOOK_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/view/book.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext request) {
        final Book book = bookService.findById(Long.parseLong((String) request.getParameter("id")));
        request.setAttribute(BOOK_ATTRIBUTE_NAME, book);
        return BOOK_PAGE_RESPONSE;
    }
}
