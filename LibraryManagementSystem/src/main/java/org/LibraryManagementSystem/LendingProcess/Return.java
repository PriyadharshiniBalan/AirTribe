package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Members.Patron;

public class Return {
    public void executeReturn(Book book, Patron person, BorrowingService history){
        if(!book.isBookBorrowed()){
            Logger.LogError("Book has not been checked out to return now: " + book);
            return;
        }
        person.returnBook(book, history);
        book.setBookBorrowed(false);
        Logger.LofInfo("Book returned : " + book);
    }
}
