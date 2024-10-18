package org.LibraryManagementSystem.Interfaces;

import org.LibraryManagementSystem.Library.LibraryItems.Book;
import java.util.List;

public interface SearchStrategy {
    List<Book> search(InventoryService inventory, String query);
}
