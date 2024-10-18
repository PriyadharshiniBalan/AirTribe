package org.LibraryManagementSystem.Members;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Interfaces.Observer;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchContext;
import java.util.ArrayList;
import java.util.List;

public class Patron extends User implements Observer {
    private String address;
    private List<Book> borrowedBooks;

    public Patron(UserData userData, SearchContext searchContext, String address){
        super(userData, searchContext);
        this.address = address;
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void borrowBook(Book book, BorrowingService history){
        this.borrowedBooks.add(book);
        history.recordBorrow(book,this);
    }

    public void returnBook(Book book, BorrowingService history){
        this.borrowedBooks.remove(book);
        history.recordReturn(book,this);
    }

    public void update(String isbn) {
        Logger.LofInfo("Book with ISBN " + isbn + " is now available for checkout! Notifying " + this.getName());
    }
}
