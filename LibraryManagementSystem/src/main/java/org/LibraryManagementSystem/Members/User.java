package org.LibraryManagementSystem.Members;
import java.util.List;
import org.LibraryManagementSystem.Logging.*;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import org.LibraryManagementSystem.Interfaces.SearchStrategy;
import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchContext;
import org.LibraryManagementSystem.SearchingProcess.Factory.SearchStrategyFactory;

public abstract class User {
    private UserData userData;
    private SearchContext searchContext;

    public User(UserData userData, SearchContext searchContext){
        this.userData = userData;
        this.searchContext = searchContext;
    }

    public int getId() {
        return userData.getId();
    }

    public String getName() {
        return userData.getName();
    }

    public void setPassword(String password) {
        this.userData.setPassword(password);
    }

    public List<Book> getAllBooksInInventory(InventoryService inventory){
        return inventory.getAllBooks();
    }

    public List<Book> getAvailableBooksInInventory(InventoryService inventory){
        return inventory.getAvailableBooks();
    }

    public List<Book> searchBooksInInventory(SearchStrategy strategy, InventoryService inventory, String query){
        this.searchContext.setSearchStrategy(strategy);
        return this.searchContext.executeSearch(inventory, query);
    }

    public List<Book> searchBooksInInventory(InventoryService inventory, String query){
        return this.searchBooksInInventory(SearchStrategyFactory.getSearchStrategy("isbn"),inventory,query);
    }

    public void viewCompleteInventoryDetails(InventoryService inventory){
        viewAllBooksInInventory(inventory);
        viewAvailableBookInInventory(inventory);
    }

    public void viewAllBooksInInventory(InventoryService inventory){
        List<Book> books = this.getAllBooksInInventory(inventory);
        if(books.isEmpty()){
            Logger.LofInfo("-------"+ this.getName() +": NO BOOKS PRESENT----");
        }
        else{
            System.out.println("-------"+ this.getName() +": ALL BOOKS DETAILS----");
            for(Book book : this.getAllBooksInInventory(inventory)){
                PrintDetails.print(book);
            }
        }
    }

    public void viewAvailableBookInInventory(InventoryService inventory){
        List<Book> books = this.getAvailableBooksInInventory(inventory);
        if(books.isEmpty()){
            Logger.LofInfo("-------"+ this.getName() +": NO AVAILABLE BOOKS PRESENT----");
        }
        else{
            System.out.println("-------"+ this.getName() +": AVAILABLE BOOKS DETAILS -----");
            for(Book book : this.getAvailableBooksInInventory(inventory)){
                PrintDetails.print(book);
            }
        }
    }
}
