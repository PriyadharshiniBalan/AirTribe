package org.LibraryManagementSystem.SearchingProcess.Factory;

import org.LibraryManagementSystem.Interfaces.SearchStrategy;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchByAuthor;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchByISBN;
import org.LibraryManagementSystem.SearchingProcess.SearchStrategy.SearchByTitle;

public class SearchStrategyFactory {
    public static SearchStrategy getSearchStrategy(String type) {
        switch (type.toLowerCase()) {
            case "title":
                return new SearchByTitle();
            case "author":
                return new SearchByAuthor();
            case "isbn":
                return new SearchByISBN();
            default:
                throw new IllegalArgumentException("Invalid search type: " + type);
        }
    }
}
