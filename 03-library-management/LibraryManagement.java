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
        if (!isAvailable) {
            isAvailable = true;
        }
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
    static final int BOOKLIMIT = 3;
    Book[] borrowedBooks = new Book[BOOKLIMIT];

    User(String name, int userId) {
        this.name = name;
        this.userId = userId; 
    }

    void borrowBook(Book book) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == book) {
                System.out.println("You already have the book!");
                break;
            } else if (borrowedBooks[i] == null) {
                borrowedBooks[i] = book;
                break;
            } else if (i == borrowedBooks.length - 1) {
                System.out.println("You've reached the limit of issuing books!");
            }
        }
    }

    void returnBook(Book book) {
        boolean isFound = false;

        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == book) {
                borrowedBooks[i] = null;
                isFound = true; 
                break;
            }
        }

        if (isFound) {
            System.out.println(book.title + " returned successfully!");
        } else {
            System.out.println("You don't have the book!");
        }
    }

    void displayUserDetails() {
        boolean hasBooks = false;
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Borrowed Books: ");
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] != null) {
                System.out.println("-" + borrowedBooks[i].title);
                hasBooks = true;
            }
        }
        if (!hasBooks) {
            System.out.println("None");
        }
    }


}


public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book book1 = new Book(101, "ABC", "Mika");
        book1.displayBookDetails();
        book1.issueBook();
        // book1.displayBookDetails();
        // book1.displayBookDetails();

        User user1 = new User("John", 1);
        user1.borrowBook(book1);
        user1.borrowBook(book1);
        user1.displayUserDetails();
        // user1.returnBook(book1);
        user1.displayUserDetails();


        sc.close();
    }
}
