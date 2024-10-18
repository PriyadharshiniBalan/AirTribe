package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Members.Patron;

public class Checkout {
    public void executeCheckout(Book book, Patron person, BorrowingService history){
        if (book.isBookBorrowed()){
            Logger.LogError("Book not available for checkout: " + book);
            return;
        }
        person.borrowBook(book, history);
        book.setBookBorrowed(true);
        Logger.LofInfo("Book is checked out: " + book);
    }
}
