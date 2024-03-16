package com.interview.library.execption;

public class BookDataMismatchException extends RuntimeException {

    public BookDataMismatchException(String message) {
        super(message);
    }
}
