package org.LibraryManagementSystem.Interfaces;

import org.LibraryManagementSystem.Library.LibraryItems.Book;
import java.util.List;
import java.util.function.Predicate;

public interface InventoryService {
    List<Book> getAllBooks();
    void addBook(Book book);
    void removeBook(String isbn);
    void removeBook(Book book);
    List<Book> filterBooks(Predicate<Book> predicate);
    List<Book> getAvailableBooks();
    List<Book> getBorrowedBooks();
}
