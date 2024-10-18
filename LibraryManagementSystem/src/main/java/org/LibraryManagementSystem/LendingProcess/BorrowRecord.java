package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Members.Patron;

import java.util.Date;

public class BorrowRecord {
    private final Book book;
    private final Patron patron;
    private final Date checkOutDate;
    private Date returnDate;

    public BorrowRecord(Book book, Date checkOutDate, Date returnDate, Patron patron){
        this.book = book;
        this.patron = patron;
        this.checkOutDate = checkOutDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
