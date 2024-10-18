package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Members.Patron;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchContext;

import java.util.List;

public class Lending {
    private InventoryService inventory;
    private Return returnService;
    private Checkout checkoutService;
    private SearchContext searchContext;

    public Lending(InventoryService inventory, SearchContext searchContext, Return returnService, Checkout checkoutService){
        this.inventory = inventory;
        this.searchContext = searchContext;
        this.returnService = returnService;
        this.checkoutService = checkoutService;
    }

    public void checkOutBook(String isbn, Patron person, BorrowingService history){
        validateParameters(isbn, person, history);
        Book book = findBookByIsbn(isbn);
        if(book != null){
            checkoutService.executeCheckout(book, person, history);
        }
    }

    public void returnBook(String isbn, Patron person, Reservation reservation, BorrowingService history){
        validateParameters(isbn, person, history);
        Book book = findBookByIsbn(isbn);
        if(book!=null){
            returnService.executeReturn(book, person, history);
            if (reservation != null) {
                reservation.notifyWhenAvailable(isbn);
            }
        }
    }

    private Book findBookByIsbn(String isbn) {
        List<Book> bookList = searchContext.executeSearch(inventory, isbn);
        if (bookList.isEmpty()) {
            Logger.LogError("Book with ISBN: " + isbn + " not present in inventory");
            return null;
        }
        return bookList.get(0);
    }

    private void validateParameters(String isbn, Patron person, BorrowingService history) {
        if (isbn == null || person == null || history == null) {
            throw new IllegalArgumentException("ISBN, patron, and borrowing history cannot be null.");
        }
    }
}
