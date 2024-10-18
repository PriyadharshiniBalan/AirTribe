package org.LibraryManagementSystem;

import org.LibraryManagementSystem.Interfaces.BorrowingService;
import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Logging.PrintDetails;
import org.LibraryManagementSystem.Members.*;
import org.LibraryManagementSystem.LendingProcess.*;
import org.LibraryManagementSystem.SearchingProcess.Factory.SearchStrategyFactory;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.*;
import org.LibraryManagementSystem.Library.LibraryItems.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InventoryService inventory = org.LibraryManagementSystem.Library.Inventory.getInstance();
        BorrowingService historyService = new org.LibraryManagementSystem.LendingProcess.BorrowingHistory();
        SearchContext searchContext = new SearchContext(SearchStrategyFactory.getSearchStrategy("isbn"));

        //Books creation
        Book book1 = new Book("The Grass is Always Greener", "Jeffrey Archer", "1-86092-049-7", "Modern Times",1867);
        Book book2 = new Book("Murder!", "Arnold Bennett ", "1-86092-012-8", "Crime",1931);
        Book book3 = new Book("An Occurrence at Owl Creek Bridge One of the Missing", "Ambrose Bierce", "1-86092-006-3", "Modern Times",1842);
        Book book4 = new Book("The Higgler", "A. E. Coppard", "1-86092-010-1", "Romance",1957);
        Book book5 = new Book("Test", "Test", "Test", "Test",0);
        Book book6 = new Book("Test", "Test", "Test", "Test",0);

        //Members creation
        Admin admin = new Admin(new UserData(1,"Admin", "Admin123"),searchContext);

        Patron person1 = new Patron(new UserData(1,"Priya", "password123"),searchContext,"Ooty");
        Patron person2 = new Patron(new UserData(2,"Nabushan", "password123"),searchContext,"Chennai");
        Patron person3 = new Patron(new UserData(3,"Balamurugan", "password123"),searchContext,"Coimbatore");

        //Admin functionality
        addBooksToInventory(admin,inventory,book1,book2,book3,book4,book5,book6);
        admin.removeBookFromInventory(inventory,book5);
        admin.removeBookFromInventory(inventory,"Test");
        admin.viewCompleteInventoryDetails(inventory);

        //Members search - Strategy Pattern
        //ADMIN - Default ISBN
        List<Book> books = admin.searchBooksInInventory(inventory,"1-86092-049-7"); // Default - ISBN
        System.out.println("-------------Book Details Search by DEFAULT - ISBN-------------------");
        for(Book book : books){
            PrintDetails.print(book);
        }

        //PATRON - Search by Title
        books = person1.searchBooksInInventory(SearchStrategyFactory.getSearchStrategy("title"),inventory,"Murder!");
        System.out.println("-------------Book Details Search by TITLE ---------------------------");
        for (Book book: books){
            PrintDetails.print(book);
        }

        //ADMIN - Search by Author
        books = admin.searchBooksInInventory(SearchStrategyFactory.getSearchStrategy("author"),inventory,"A. E. Coppard");
        System.out.println("-------------Book Details Search by AUTHOR ---------------------------");
        for (Book book: books){
            PrintDetails.print(book);
        }

        //Lending service creation
        searchContext.setSearchStrategy(SearchStrategyFactory.getSearchStrategy("isbn"));
        Lending lendingServices = new Lending(inventory,searchContext,new Return(), new Checkout());
        lendingServices.checkOutBook("1-86092-049-7", person1, historyService);
        lendingServices.checkOutBook("1-86092-010-1", person1, historyService);
        lendingServices.checkOutBook("1-86092-049-7", person2, historyService);
        lendingServices.checkOutBook("1-86092-012-8", person2, historyService);

        //Patron functionality
        person1.viewCompleteInventoryDetails(inventory);
        System.out.println("--------------------------PATRON DETAILS------------------------------");
        PrintDetails.print(person1);
        PrintDetails.print(person2);
        PrintDetails.print(person3);

        Reservation reservation = new Reservation(inventory, searchContext);
        reservation.reserveBook("1-86092-049-7", person2);
        reservation.reserveBook("1-86092-049-7", person3);
        reservation.reserveBook("1-86092-006-3", person3);

        lendingServices.returnBook("1-86092-049-7", person1, reservation, historyService);

        System.out.println("Total Books: " + inventory.getAllBooks().size());
        System.out.println("Available Books: " + inventory.getAvailableBooks().size());
        System.out.println("Borrowed Books: " + inventory.getBorrowedBooks().size());

        System.out.println("--------------------------PATRON DETAILS------------------------------");
        PrintDetails.print(person1);
        PrintDetails.print(person2);
        PrintDetails.print(person3);

        System.out.println("------------------------BORROWING HISTORY DETAILS------------------------------");
        for(BorrowRecord hist : historyService.getBorrowHistoryForPatron(person1)){
            PrintDetails.print(hist);
        }
    }

    private static void addBooksToInventory(Admin admin, InventoryService inventory, Book ... books){
        for(Book book : books){
            admin.addBookToInventory(inventory,book);
        }
    }
}
