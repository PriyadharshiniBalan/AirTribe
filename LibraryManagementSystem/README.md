Library Management System Design Documentation

**1. Introduction**
-------------------
  The Library Management System (LMS) is designed to facilitate the management of library resources, including books, patrons, and borrowing processes. This document provides an overview of the system architecture, key components, design patterns used, and interfaces.

**2. Objectives**
---------------
  To manage library inventory efficiently.
  To track borrowing and returning of books.
  To support multiple search strategies for finding books.
  To handle user authentication and roles (Admin and Patron).
  To maintain borrowing history for each patron.

**3. System Architecture**
--------------------------
  The LMS is structured using a layered architecture with the following layers:

Presentation Layer: 
  User interface and interaction logic.
Service Layer: 
  Business logic, including borrowing, returning, and inventory management.
Data Layer: 
  Data access and storage, managing books and user information.

3.1 Components
---------------
Entities: 
  Represents core objects in the system, such as Book, Patron, Administrator, and BorrowHistory.
Services: 
  Contains business logic for managing entities, such as Lending, BorrowingHistory, and Reservation.
Interfaces: 
  Abstract definitions for components, enabling flexible implementation. Examples include IInventory, ILogger, and ISearchStrategy.

**4. Design Patterns Used**
----------------------------
Singleton Pattern: 
  Used for the Inventory class to ensure a single instance manages all books in the library.
Strategy Pattern: 
  Employed in the SearchContext class to allow different search strategies for finding books (e.g., by ISBN, title, or author).
Observer Pattern: 
  Implemented via the IObserver interface, allowing components to subscribe to changes (e.g., reservations).
Factory Pattern:
  Implemented in the creation of SearchStrategy objects.

**5. Class Diagram**
----------------------
  A UML class diagram illustrating the relationships between classes and interfaces (not included here, but you can create one using diagramming tools).

**6. Component Descriptions**
-----------------------------

6.1 Entities
---------------
  Book
    Attributes: Title, Author, ISBN, Genre, Publication Year
    Methods: Getters and Setters

  Patron
    Attributes: UserData (ID, Name, Password), Address
    Methods: viewCompleteInventoryDetails, searchBooksInInventory

  Administrator
    Attributes: UserData (ID, Name, Password)
    Methods: addBookToInventory, removeBookFromInventory, viewCompleteInventoryDetails

6.2 Services
---------------
  Inventory
    Methods: getAllBooks, addBook, removeBook, filterBooks, getAvailableBooks, getBorrowedBooks

  Lending
    Methods: checkOutBook, returnBook

  BorrowingHistory
    Methods: recordBorrow, recordReturn, getBorrowHistoryForPatron

6.3 Interfaces
---------------
  IInventory
  ILogger
  IBorrowingHistory
  ISearchStrategy
  IObserver

**7. Logging**
----------------
  The system uses a custom logger class for logging events and actions within the application, ensuring that critical operations are recorded for auditing and debugging.

**8. Deployment**
------------------
  The application can be deployed as a standalone Java application. Dependencies are managed using Gradle.

**10. Conclusion**
---------------
  This design document provides a comprehensive overview of the Library Management System, outlining its architecture, key components, design patterns, and testing strategies. The modular design allows for easy extension and maintenance of the system.
