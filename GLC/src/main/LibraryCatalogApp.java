package main;

//public class Main {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
//import java.util.ArrayList;
//import java.util.List;
//
//// Define a generic class for library items
//class LibraryItem<T> {
//    private T item;
//
//    public LibraryItem(T item) {
//        this.item = item;
//    }
//
//    public T getItem() {
//        return item;
//    }
//}
//
//// Define the LibraryCatalog class
//class LibraryCatalog<T> {
//    private List<LibraryItem<T>> catalog;
//
//    public LibraryCatalog() {
//        catalog = new ArrayList<>();
//    }
//
//    // Add an item to the catalog
//    public void addItem(LibraryItem<T> item) {
//        catalog.add(item);
//    }
//
//    // Remove an item from the catalog
//    public void removeItem(LibraryItem<T> item) {
//        catalog.remove(item);
//    }
//
//    // Display all items in the catalog
//    public void displayCatalog() {
//        for (LibraryItem<T> item : catalog) {
//            System.out.println(item.getItem());
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Example usage
//        LibraryCatalog<String> stringCatalog = new LibraryCatalog<>();
//        stringCatalog.addItem(new LibraryItem<>("Book: Harry Potter"));
//        stringCatalog.addItem(new LibraryItem<>("DVD: Inception"));
//        stringCatalog.addItem(new LibraryItem<>("Magazine: National Geographic"));
//
//        System.out.println("Library Catalog:");
//        stringCatalog.displayCatalog();
//    }
//}

import java.util.ArrayList;
import java.util.List;

// Generic LibraryItem class
class LibraryItem<T> {
    private String title;
    private String author;
    private int itemID;
    
    // Constructor
    public LibraryItem(String title, String author, int itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }
    
    // Getters and Setters (if needed)
    
    // Override toString() for easy printing
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ItemID: " + itemID;
    }
}

// Generic Catalog class
class Catalog<T extends LibraryItem<?>> {
    private List<T> items;

    // Constructor
    public Catalog() {
        items = new ArrayList<>();
    }

    // Method to add a new library item to the catalog
    public void addItem(T item) {
        items.add(item);
    }

    // Method to remove a library item from the catalog
    public void removeItem(T item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            System.out.println("Item not found in the catalog.");
        }
    }

    // Method to retrieve item details
    public void viewCatalog() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}

// Main class for the user interface
public class LibraryCatalogApp {
    public static void main(String[] args) {
        // Create a generic catalog
        Catalog<LibraryItem<?>> libraryCatalog = new Catalog<>();

        // Add some sample items
        LibraryItem<Book> book = new LibraryItem<>("Java Programming", "John Doe", 101);
        LibraryItem<DVD> dvd = new LibraryItem<>("Introduction to Java", "Jane Smith", 201);

        // Add items to the catalog
        libraryCatalog.addItem(book);
        libraryCatalog.addItem(dvd);

        // View the catalog
        System.out.println("Library Catalog:");
        libraryCatalog.viewCatalog();

        // Remove an item
        libraryCatalog.removeItem(book);

        // View the updated catalog
        System.out.println("\nUpdated Catalog:");
        libraryCatalog.viewCatalog();
    }
}

