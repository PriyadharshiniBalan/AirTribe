package org.LibraryManagementSystem.Members;

import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Logging.Logger;
import org.LibraryManagementSystem.Logging.PrintDetails;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchContext;

import java.util.List;

public class Admin extends User{

    public Admin(UserData userData, SearchContext searchContext){
        super(userData,searchContext);
    }

    public void addBookToInventory(InventoryService inventory, Book book){
        inventory.addBook(book);
        Logger.LofInfo("Admin added book: " + book.getTitle());
    }

    public void removeBookFromInventory(InventoryService inventory, String isbn){
        inventory.removeBook(isbn);
        Logger.LofInfo("Admin removed book with ISBN: " + isbn);
    }

    public void removeBookFromInventory(InventoryService inventory, Book book){
        inventory.removeBook(book);
        Logger.LofInfo("Admin removed book: " + book.getTitle());
    }

    public List<Book> getBorrowedBooksFromInventory(InventoryService inventory){
        return inventory.getBorrowedBooks();
    }

    @Override
    public void viewCompleteInventoryDetails(InventoryService inventory){
        super.viewCompleteInventoryDetails(inventory);
        viewBorrowedBooksFromInventory(inventory);
    }

    public void viewBorrowedBooksFromInventory(InventoryService inventory){
        List<Book> books = this.getBorrowedBooksFromInventory(inventory);
        if(books.isEmpty()){
            Logger.LofInfo("-------"+ this.getName() +": NO BORROWED BOOKS ----");
        }
        else {
            System.out.println("-------"+ this.getName() +": BORROWED BOOKS DETAILS----");
            for(Book book : this.getBorrowedBooksFromInventory(inventory)){
                PrintDetails.print(book);
            }
        }
    }
}