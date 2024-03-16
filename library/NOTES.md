# Design Documents

## Library Management System

### Project Structure
This project uses a layer architecture with separation of concerns:
* Controller: Handles user interaction (e.g., Spring Shell commands) and interacts with the service layer.

* Service: Implements core business logic and interacts with the repository layer.

* Repository: Provides access to the data persistence layer (use the built-in db H2).

* Entity: Represents data objects (e.g., Book) with attributes and accessors.

### Exception Handling (Enhancement)
* Specific exceptions like BookDataMismatchException, BookNotFoundException are created for application-related error.

* These exceptions provide informative messages for debugging and user experience.

### Spring Shell Commands
* Spring Shell provides a command-line interface for interacting with the library system. Users can execute commands to manage books (e.g., ADD_BOOK, CHECKOUT_BOOK, LIST_AVAILABLE_BOOKS, RETURN_BOOKS).
