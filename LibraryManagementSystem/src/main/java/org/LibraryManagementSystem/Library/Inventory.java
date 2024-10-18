package org.LibraryManagementSystem.Library;

import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements InventoryService {
    private List<Book> bookList;
    private static Inventory instance;

    private Inventory(){
        this.bookList = new ArrayList<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            synchronized (Inventory.class) {
                if (instance == null) {
                    instance = new Inventory();
                }
            }
        }
        return instance;
    }

    public List<Book> getAllBooks() {
        return bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }

    public void removeBook(String isbn){
        this.bookList.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public void removeBook(Book book){
        this.bookList.remove(book);
    }

    public List<Book> filterBooks (Predicate<Book> predicate){
        List<Book> filteredBooks = new ArrayList<>();
        for(Book book: bookList){
            if(predicate.test(book)){
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public List<Book> getAvailableBooks(){
        return filterBooks(book -> !book.isBookBorrowed());
    }

    public List<Book> getBorrowedBooks(){
        return filterBooks(book -> book.isBookBorrowed());
    }
}
