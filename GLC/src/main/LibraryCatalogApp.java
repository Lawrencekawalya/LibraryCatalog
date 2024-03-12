package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


//Generic LibraryItem class
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

 // Override equals method for item comparison based on itemID
 @Override
 public boolean equals(Object obj) {
     if (this == obj) {
         return true;
     }
     if (obj == null || getClass() != obj.getClass()) {
         return false;
     }
     LibraryItem<?> otherItem = (LibraryItem<?>) obj;
     return itemID == otherItem.itemID;
 }
}

// Additional classes for different types of library items

class Book extends LibraryItem<Book> {
    // Additional attributes specific to books (if needed)
    
    // Constructor
    public Book(String title, String author, int itemID) {
        super(title, author, itemID);
        // Additional initialization for books
    }
}

class DVD extends LibraryItem<DVD> {
    // Additional attributes specific to DVDs (if needed)
    
    // Constructor
    public DVD(String title, String author, int itemID) {
        super(title, author, itemID);
        // Additional initialization for DVDs
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
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in the catalog. Unable to remove.");
        }
    }

    // Method to retrieve item details
    public void viewCatalog() {
        if (items.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            System.out.println("Library Catalog:");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }
}

//Main class for the user interface
public class LibraryCatalogApp {
 public static void main(String[] args) {
     // Create a generic catalog
     Catalog<LibraryItem<?>> libraryCatalog = new Catalog<>();

     // Scanner for user input
     Scanner scanner = new Scanner(System.in);

     int choice;
     do {
         // Display menu
         System.out.println("\nLibrary Catalog Menu:");
         System.out.println("1. Add a new library item");
         System.out.println("2. Remove an item");
         System.out.println("3. View the current catalog");
         System.out.println("0. Exit");

         // Get user choice
         System.out.print("Enter your choice: ");
         choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 // Add a new library item               
            	 System.out.println("Enter item details:");
            	 System.out.print("Title: ");
            	 // Consume the newline character left from the previous input
            	 scanner.nextLine();
            	 String title = scanner.nextLine();
            	 System.out.print("Author: ");
            	 String author = scanner.nextLine();
            	 System.out.print("ItemID: ");
            	 int itemID = scanner.nextInt();

                 // Create a new LibraryItem (you can choose Book, DVD, etc. based on your hierarchy)
                 LibraryItem<?> newItem = new LibraryItem<>(title, author, itemID);

                 // Add the item to the catalog
                 libraryCatalog.addItem(newItem);
                 System.out.println("Item added successfully.");
                 break;

             case 2:
            	    // Remove an item
            	    System.out.print("Enter the ItemID of the item to remove: ");
            	    int removeItemID = scanner.nextInt();

            	    // Create a dummy item with the given ItemID for comparison
            	    LibraryItem<?> itemToRemove = new LibraryItem<>("", "", removeItemID);

            	    // Remove the item from the catalog
            	    libraryCatalog.removeItem(itemToRemove);
            	    break;

             case 3:
                 // View the current catalog
                 libraryCatalog.viewCatalog();
                 break;

             case 0:
                 // Exit the program
                 System.out.println("Exiting the Library Catalog App. Goodbye!");
                 break;

             default:
                 System.out.println("Invalid choice. Please enter a valid option.");
         }

     } while (choice != 0);

     // Close the scanner
     scanner.close();
 }
}



