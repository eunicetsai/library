package com.interview.library.service;

import java.util.List;

import com.interview.library.entity.Book;
import com.interview.library.execption.BookDataMismatchException;
import com.interview.library.execption.BookNotFoundException;
import com.interview.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAvailableBooks() {
        return bookRepository.findAllByCopiesGreaterThan(0, Sort.by("title"));
    }

    @Override
    public void addBook(Long id, String title, String author, int copies) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if (!book.getTitle().equals(title) || !book.getAuthor().equals(author)) {
                throw new BookDataMismatchException("Book with ID " + id + " already exists with different title or author!");
            }
            book.setCopies(book.getCopies() + copies);
            bookRepository.save(book);
            return;
        } else {
            Book newBook = new Book(id, title, author, copies);
            bookRepository.save(newBook);
            return;
        }
    }

    @Override
    public String checkoutBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if (book.getCopies() > 0) {
                book.setCopies(book.getCopies() - 1);
                bookRepository.save(book);
                return "Checked out " + book.getTitle();
            } else {
                return "Book not available";
            }
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    @Override
    public String returnBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setCopies(book.getCopies() + 1);
            bookRepository.save(book);
            return "Returned " + book.getTitle();
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }


}
