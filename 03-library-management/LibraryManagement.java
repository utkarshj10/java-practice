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

    boolean borrowBook(Book book) {
        boolean isAdded = false;

        for (int i = 0; i < borrowedBooks.length; i++) {

            if (borrowedBooks[i] == book) {
                System.out.println("You already have the book!");
                return false;
            }

            if (borrowedBooks[i] == null) {
                borrowedBooks[i] = book;
                isAdded = true;
                break;
            }
        }

        if (!isAdded) {
            System.out.println("You've reached the limit of issuing books!");
            return false;
        }

        return true;
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

class Library {
    static final int TOTAL_BOOKS = 50;
    static final int TOTAL_USERS = 20;
    Book[] books = new Book[TOTAL_BOOKS];
    User[] users = new User[TOTAL_USERS];

    void addBook(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            } else if (i == books.length - 1) {
                System.out.println("Library is full! Cannot add more books.");
            }
        }
    }

    void addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                break;
            } else if (i == users.length - 1) {
                System.out.println("Maximum User Limit Reached! Cannot add more users.");
            }
        }
    }

    Book searchBook(int bookId) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].bookId == bookId) {
                return books[i];
            }
        }
        return null;
    }

    User searchUser(int userId) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].userId == userId) {
                return users[i];
            }
        }
        return null;
    }

    void issueBook(int bookId, int userId) {
        Book book = searchBook(bookId);
        User user = searchUser(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is not available for issuing!");
            return;
        }

        if (user.borrowBook(book)) {
            book.issueBook();
            System.out.println("Book issued successfully!");
        }
    }

    void returnBook(int bookId, int userId) {
        Book book = searchBook(bookId);
        User user = searchUser(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        boolean hasBook = false;

        for (Book borrowedBook : user.borrowedBooks) {
            if (borrowedBook != null && borrowedBook.bookId == bookId) {
                hasBook = true;
                break;
            }
        }

        if (!hasBook) {
            System.out.println("You don't have the book!");
            return;
        }

        user.returnBook(book);
        book.returnBook();

        System.out.println("Book returned successfully!");
    }
}


public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        sc.close();
    }
}
