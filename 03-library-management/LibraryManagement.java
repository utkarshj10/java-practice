import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isAvailable = true;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    boolean isAvailable() {
        return isAvailable;
    }

    void issueBook() {
        if (isAvailable) {
            isAvailable = false;
        }
    }

    void returnBook() {
        if (!isAvailable)
        isAvailable = true;
    }

    void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}

class User {
    String name;
    int userId;
    Book[] borrowedBooks;
}


public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Book book1 = new Book(101, "ABC", "Mika");
        // book1.displayBookDetails();
        // book1.issueBook();
        // book1.displayBookDetails();
        // book1.returnBook();
        // book1.displayBookDetails();

        sc.close();
    }
}
