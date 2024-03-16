package com.interview.library.service;

import com.interview.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> listAvailableBooks();
    void addBook(Long id, String title, String author, int copies);

    String checkoutBook(Long id);

    String returnBook(Long id);

}

