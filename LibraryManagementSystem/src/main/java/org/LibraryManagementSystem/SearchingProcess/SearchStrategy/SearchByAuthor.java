package org.LibraryManagementSystem.SearchingProcess.SearchStrategy;

import org.LibraryManagementSystem.Interfaces.InventoryService;
import org.LibraryManagementSystem.Interfaces.SearchStrategy;
import org.LibraryManagementSystem.Library.LibraryItems.Book;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByAuthor implements SearchStrategy {
    @Override
    public List<Book> search(InventoryService inventory, String author) {
        return inventory.getAllBooks().stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }
}
