package org.LibraryManagementSystem.LendingProcess;

import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Members.Patron;
import org.LibraryManagementSystem.Interfaces.Observer;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    private Map<String, List<Observer>> reservations;
    private InventoryService inventory;
    private SearchContext searchContext;

    public Reservation(InventoryService inventory, SearchContext searchContext){
        this.reservations = new HashMap<>();
        this.inventory = inventory;
        this.searchContext = searchContext;
    }

    public Map<String, List<Observer>> getReservations() {
        return reservations;
    }

    public void reserveBook(String isbn, Patron patron){
        List<Book> bookList = this.searchContext.executeSearch(inventory,isbn);
        if(bookList.isEmpty()){
            Logger.LogError("Book with ISBN " + isbn + " is not present in inventory"+ patron.getName());
            return;
        }

        Book book = bookList.get(0);
        if(!book.isBookBorrowed()){
            Logger.LofInfo("Book with ISBN " + isbn + " is available for checkout! - " + patron.getName());
            return;
        }

        if(reservations.containsKey(isbn)){
            if(reservations.get(isbn).contains(patron)){
                Logger.LofInfo("Book with ISBN " + isbn + " is already been reserved by you !");
            }
            else{
                reservations.get(isbn).add(patron);
            }
        }
        else{
            reservations.put(isbn,new ArrayList<>());
            reservations.get(isbn).add(patron);
        }
        Logger.LofInfo("Book with ISBN " + isbn + " is reserved by : " + patron.getName());
    }

    //Observer Design Pattern
    public void notifyWhenAvailable(String isbn){
        if(reservations.containsKey(isbn)){
            List<Observer> observerList = reservations.get(isbn);
            for(Observer observer: observerList){
                observer.update(isbn);
            }
            reservations.remove(isbn);
        }
    }
}
