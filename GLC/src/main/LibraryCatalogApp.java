package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

 // Getter method for itemID
 public int getItemID() {
     return itemID;
 }

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

//classes for different types of library items
class Book extends LibraryItem<Book> {  
    // Constructor
    public Book(String title, String author, int itemID) {
        super(title, author, itemID);
        // Additional initialization for books
    }
}

class DVD extends LibraryItem<DVD> {    
    // Constructor
    public DVD(String title, String author, int itemID) {
        super(title, author, itemID);
        // Additional initialization for DVDs
    }
}

//Generic Catalog class
class Catalog<T extends LibraryItem<?>> {
 private List<T> items;

 // Constructor
 public Catalog() {
     items = new ArrayList<>();
 }

 // Method to add a new library item to the catalog
 public void addItem(T item, String itemType) {
     items.add(item);
     System.out.println("New " + itemType + " added successfully.");
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

         // Separate books and DVDs
         List<T> books = new ArrayList<>();
         List<T> dvds = new ArrayList<>();

         // Separate items based on type
         for (T item : items) {
             if (item instanceof Book) {
                 books.add(item);
             } else if (item instanceof DVD) {
                 dvds.add(item);
             }
         }

         // Display books
         if (!books.isEmpty()) {
             System.out.println("\nBooks:");
             for (T book : books) {
                 System.out.println(book);
             }
         } else {
             System.out.println("No books in the catalog.");
         }

         // Display DVDs
         if (!dvds.isEmpty()) {
             System.out.println("\nDVDs:");
             for (T dvd : dvds) {
                 System.out.println(dvd);
             }
         } else {
             System.out.println("No DVDs in the catalog.");
         }
     }
 }

 // Method to get the list of items
 public List<T> getItems() {
     return items;
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

            // Get user choice with exception handling
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Catch exception if user input is not an integer
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set choice to an invalid value to repeat the loop
            }

            switch (choice) {
                case 1:
                    // Add a new library item
                    System.out.println("Enter item details:");
                    System.out.print("Title: ");
                    scanner.nextLine(); // Consume the newline character left from the previous input
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();

                    // Get itemID with exception handling
                    int itemID = 0;
                    try {
                        System.out.print("ItemID: ");
                        itemID = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        // Catch exception if user input for itemID is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for itemID.");
                        scanner.nextLine(); // Consume the invalid input
                        break; // Exit the current case and repeat the loop
                    }

                    // Prompt user to specify item type (book or DVD)
                    System.out.println("Enter item type (1 for Book, 2 for DVD): ");
                    try {
                        int itemType = scanner.nextInt();

                        LibraryItem<?> newItem;
                        if (itemType == 1) {
                            // Create a new Book object
                            newItem = new Book(title, author, itemID);
                            libraryCatalog.addItem(newItem, "book");
                        } else if (itemType == 2) {
                            // Create a new DVD object
                            newItem = new DVD(title, author, itemID);
                            libraryCatalog.addItem(newItem, "DVD");
                        } else {
                            System.out.println("Invalid item type. Item not added to catalog.");
                        }
                    } catch (InputMismatchException e) {
                        // Catch exception if user input for item type is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for item type.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                    break;
                  
                case 2:
                    // Remove an item
                    System.out.print("Enter the ItemID of the item to remove: ");
                    try {
                        int removeItemID = scanner.nextInt();

                        // Search for the item in the catalog
                        boolean found = false;
                        for (LibraryItem<?> item : libraryCatalog.getItems()) {
                            if (item.getItemID() == removeItemID) {
                                libraryCatalog.removeItem(item);
                                found = true;
                                break;
                            }
                        }

                        // If item not found, display error message
                        if (!found) {
                            System.out.println("Item not found in the catalog. Unable to remove.");
                        }
                    } catch (InputMismatchException e) {
                        // Catch exception if user input for item ID is not an integer
                        System.out.println("Invalid input. Please enter a valid integer for item ID.");
                        scanner.nextLine(); // Consume the invalid input
                    }
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





