package org.LibraryManagementSystem.SearchingProcess.SearchStrategy;

import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Interfaces.SearchStrategy;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import java.util.List;

public class SearchContext{
    private SearchStrategy searchStrategy;

    public SearchContext(SearchStrategy defaultStrategy){
        this.searchStrategy = defaultStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Book> executeSearch(InventoryService inventory, String query){
        return searchStrategy.search(inventory,query);
    }
}
