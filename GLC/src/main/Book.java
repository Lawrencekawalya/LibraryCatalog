package main;

class Book extends LibraryItem<Book> {
    // Additional attributes specific to books (if needed)
    
    // Constructor
    public Book(String title, String author, int itemID) {
        super(title, author, itemID);
        // Additional initialization for books
    }
}