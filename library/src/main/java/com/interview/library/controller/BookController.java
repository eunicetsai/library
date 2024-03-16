package com.interview.library.controller;

import com.interview.library.entity.Book;
import com.interview.library.execption.BookDataMismatchException;
import com.interview.library.execption.BookNotFoundException;
import com.interview.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(key = "LIST_AVAILABLE_BOOKS")
    public String listAvailableBooks() {
        List<Book> availableBooks = bookService.listAvailableBooks();
        StringBuilder output = new StringBuilder("List available books:\n");
        for (Book book : availableBooks) {
            output.append(book.getTitle()).append(" by ").append(book.getAuthor())
                    .append(" - ").append(book.getCopies()).append(" available\n");
        }
        return output.toString();
    }

    @ShellMethod(key = "ADD_BOOK", value = "ADD_BOOK {tile} {author} {copies}")
    public void addBook(Long id, String title, String author, int copies) {
       try {
           bookService.addBook(id, title, author, copies);
       } catch (BookDataMismatchException e) {
           System.err.println("Error adding book: " + e.getMessage());
       }
    }

    @ShellMethod(key = "CHECKOUT_BOOK", value = "CHECKOUT_BOOK {id}")
    public String checkoutBook(Long id) {
        String res = "";
        try {
            res = bookService.checkoutBook(id);
        } catch (BookNotFoundException e) {
            System.err.println("Error checking out book: " + e.getMessage());
        }
        return res ;
    }

    @ShellMethod(key = "RETURN_BOOK", value = "RETURN_BOOK {id}")
    public String returnBook(Long id) {
        String res = "";
        try {
            res = bookService.returnBook(id);
        } catch (BookNotFoundException e){
            System.err.println("Error returning book: " + e.getMessage());
        }
        return res;
    }

}
