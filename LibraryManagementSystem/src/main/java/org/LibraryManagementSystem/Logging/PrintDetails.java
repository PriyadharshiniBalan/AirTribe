package org.LibraryManagementSystem.Logging;

import org.LibraryManagementSystem.LendingProcess.BorrowRecord;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Members.Patron;

public class PrintDetails {
    public static void print(BorrowRecord history){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Book : " + history.getBook().getTitle());
        System.out.println("Patron : " + history.getPatron().getName());
        System.out.println("Checkout Date : " + history.getCheckOutDate());
        System.out.println("Return Date : " + history.getReturnDate());
        System.out.println("----------------------------------------------------------------------");
    }

    public static void print(Patron patron){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Name : " + patron.getName());
        System.out.println("Address : " + patron.getAddress());
        System.out.println("Borrowed Books Count : " + patron.getBorrowedBooks().size());
        for(Book book : patron.getBorrowedBooks()){
            System.out.println(book.toString());
        }
        System.out.println("----------------------------------------------------------------------");
    }

    public static void print(Book book){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Title : " + book.getTitle());
        System.out.println("Author : " + book.getAuthor());
        System.out.println("ISBN : " + book.getIsbn());
        System.out.println("Series : " + book.getSeries());
        System.out.println("Publication Year : " + book.getPublicationYear());
        System.out.println("Is Book Borrowed : " + book.isBookBorrowed());
        System.out.println("----------------------------------------------------------------------");
    }
}
