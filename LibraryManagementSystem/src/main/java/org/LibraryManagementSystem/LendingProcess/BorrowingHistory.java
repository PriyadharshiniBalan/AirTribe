package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Members.Patron;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowingHistory implements BorrowingService {
    private List<BorrowRecord> borrowHistoryList;

    public BorrowingHistory() {
        this.borrowHistoryList = new ArrayList<>();
    }

    public void recordBorrow(Book book, Patron patron) {
        BorrowRecord history = new BorrowRecord(book, new Date(), null, patron);
        borrowHistoryList.add(history);
        Logger.LofInfo("Borrow Recorded");
    }

    public void recordReturn(Book book, Patron patron) {
        for (BorrowRecord history : borrowHistoryList) {
            if (history.getBook().equals(book) && history.getPatron().equals(patron) && history.getReturnDate() == null) {
                history.setReturnDate(new Date());
                Logger.LofInfo("Return Recorded");
                return;
            }
        }
        Logger.LogError("No Borrow History Recorded.");
    }

    public List<BorrowRecord> getBorrowHistoryForPatron(Patron patron) {
        Logger.LofInfo("Borrow history of " + patron.getName());
        List<BorrowRecord> patronHistory = new ArrayList<>();
        if(!borrowHistoryList.isEmpty()){
            for (BorrowRecord history : borrowHistoryList) {
                if (history.getPatron().equals(patron)) {
                    patronHistory.add(history);
                }
            }
            return patronHistory;
        }
        Logger.LofInfo("No Borrow History!");
        return patronHistory;
    }
}
