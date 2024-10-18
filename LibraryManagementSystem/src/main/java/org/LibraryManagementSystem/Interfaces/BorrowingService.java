package org.LibraryManagementSystem.Interfaces;

import org.LibraryManagementSystem.LendingProcess.BorrowRecord;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Members.Patron;
import java.util.List;

public interface BorrowingService {
    void recordBorrow(Book book, Patron patron);
    void recordReturn(Book book, Patron patron);
    List<BorrowRecord> getBorrowHistoryForPatron(Patron patron);
}
