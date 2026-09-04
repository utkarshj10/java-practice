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
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == book) {
                borrowedBooks[i] = null;
                break;
            }
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

    boolean addBook(Book book) {

        if (searchBook(book.bookId) != null) {
            System.out.println("Book ID already exists!");
            return false;
        }

        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                return true;
            }
        }

        System.out.println("Library is full! Cannot add more books.");
        return false;
    }

    boolean addUser(User user) {

        if (searchUser(user.userId) != null) {
            System.out.println("User ID already exists!");
            return false;
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return true;
            }
        }

        System.out.println("Maximum User Limit Reached!");
        return false;
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

    void displayAllBooks() {
        System.out.println("Books in Library:");
        for (Book book : books) {
            if (book != null) {
                book.displayBookDetails();
                System.out.println();
            }
        }
    }

    void displayAllUsers() {
        System.out.println("All User Details:");
        for (User user : users) {
            if (user != null) {
                user.displayUserDetails();
                System.out.println();
            }
        }
    }
}


public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {

            System.out.println("===== LIBRARY MANAGEMENT =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Search Book");
            System.out.println("4. Search User");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Display All Books");
            System.out.println("8. Display All Users");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    Book book = new Book(bookId, title, author);

                    if (library.addBook(book)) {
                        System.out.println("Book added successfully!");
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();

                    User user = new User(name, userId);

                    if (library.addUser(user)) {
                        System.out.println("User added successfully!");
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    Book book = library.searchBook(bookId);

                    if (book != null) {
                        book.displayBookDetails();
                    } else {
                        System.out.println("Book not found!");
                    }

                    break;
                }

                case 4: {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();

                    User user = library.searchUser(userId);

                    if (user != null) {
                        user.displayUserDetails();
                    } else {
                        System.out.println("User not found!");
                    }

                    break;
                }

                case 5: {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    library.issueBook(bookId, userId);

                    break;
                }

                case 6: {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    library.returnBook(bookId, userId);

                    break;
                }

                case 7: {
                    library.displayAllBooks();
                    break;
                }

                case 8: {
                    library.displayAllUsers();
                    break;
                }

                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        } 
    }
}
