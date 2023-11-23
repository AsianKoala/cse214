import java.util.Scanner;

/**
 * The LibraryManager class manages all interaction between the human and the program
 * Simulates managing a library
 */
public class LibraryManager {
    private static BookRepository bookRepository;
    private static ReturnStack returnStack;
    private static Scanner s;

    /**
     * Prints the menu for human interaction
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("(B) - Manage Book Repository");
        System.out.println("\t (C) - Checkout Book");
        System.out.println("\t (N) - Add New Book");
        System.out.println("\t (R) - Remove Book");
        System.out.println("\t (P) - Print Repository");
        System.out.println("\t (S) - Sort Shelf");
        System.out.println("\t\t (I) - ISBN Number");
        System.out.println("\t\t (N) - Name");
        System.out.println("\t\t (A) - Author");
        System.out.println("\t\t (G) - Genre");
        System.out.println("\t\t (Y) - Year");
        System.out.println("\t\t (C) - Condition");
        System.out.println("(R) - Manage Return Stack");
        System.out.println("\t (R) - Return Book");
        System.out.println("\t (L) - See Last Return");
        System.out.println("\t (C) - Check In Last Return");
        System.out.println("\t (P) - Print Return Stack");
        System.out.println("(Q) - Quit");
        System.out.println();
        System.out.print("Please select what to manage: ");
    }

    /**
     * Handles checking out action
     */
    private static void handleCheckoutBook() {
        try {
            String id, isbn;
            Date dueDate, checkoutDate;

            System.out.print("Please provide a library user ID: ");
            id = s.next().trim();
            if(Util.isInvalidUserID(id)) {
                System.out.println("Error: Invalid UserID provided");
                return;
            }

            System.out.print("Please provide an ISBN number: ");
            isbn = s.next().trim();
            if(Util.isInvalidISBN(isbn)) {
                System.out.println("Error: Invalid ISBN provided");
                return;
            }

            System.out.print("Please provide a due date: ");
            dueDate = Util.parseDateString(s.next().trim());

            System.out.print("Please provide the checkout date (current date): ");
            checkoutDate = Util.parseDateString(s.next().trim());

            System.out.println("\nLoading...");

            bookRepository.checkOutBook(Util.convertISBNToLong(isbn), Util.convertIDToLong(id), dueDate, checkoutDate);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles adding a new book
     */
    private static void handleAddNewBook() {
        try {
            long isbn;
            String name, author, genre, condStr;
            Condition cond;

            System.out.print("Please provide an ISBN number: ");
            String input = s.next().trim();
            if(Util.isInvalidISBN(input)) {
                throw new InvalidISBNException();
            }
            isbn = Util.convertISBNToLong(input);

            System.out.print("Please provide a name: ");
            name = s.next().trim();

            System.out.print("Please provide an author: ");
            author = s.next().trim();

            System.out.print("Please provide a genre: ");
            genre = s.next().trim();

            System.out.print("Please provide a condition: ");
            condStr = s.next().trim();
            cond = Condition.parseString(condStr);

            System.out.print("Please provide a year: ");
            String yearStr = s.next().trim();
            int year = Integer.parseInt(yearStr);

            System.out.println("\nLoading...");

            bookRepository.addBook(isbn, name, author, genre, cond, year);

            System.out.println("Successfully added book " + name + " to the book repository!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles removing a book
     */
    private static void handleRemoveBook() {
        try {
            String isbn;
            System.out.print("Please provide an ISBN number: ");
            isbn = s.next().trim();

            if(Util.isInvalidISBN(isbn)) {
                throw new InvalidISBNException();
            }

            long isbnLong = Util.convertISBNToLong(isbn);
            if(bookRepository.checkDoesNotExist(isbnLong)) {
                throw new BookDoesNotExistException();
            }
            String name = bookRepository.fetch(isbnLong).getName();

            System.out.println("\nLoading...");
            bookRepository.removeBook(isbnLong);

            System.out.println(name + " has been successfully removed from the book repository!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles printing a shelf in the repository
     */
    private static void handlePrintRepo() {
        try {
            String shelf;
            System.out.print("Please select a shelf: ");
            shelf = s.next().trim();
            int shelfInt = Util.parseShelfString(shelf);

            System.out.println("\nLoading...");

            bookRepository.printShelf(shelfInt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles sorting the shelf
     */
    private static void handleSortShelf() {
        try {
            String shelf, sc;
            System.out.print("Please select a shelf: ");
            shelf = s.next().trim();
            System.out.print("Please select a sorting criteria: ");
            sc = s.next().trim();
            int shelfInd = Util.parseShelfString(shelf);

            System.out.println("\nLoading...");

            bookRepository.sortShelf(shelfInd, sc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the bookRepository interactions
     */
    private static void handleBookRepo() {
        System.out.print("Please select an option: ");
        String input = s.next().trim();
        switch (input) {
            case "C":
                handleCheckoutBook();
                break;
            case "N":
                handleAddNewBook();
                break;
            case "R":
                handleRemoveBook();
                break;
            case "P":
                handlePrintRepo();
                break;
            case "S":
                handleSortShelf();
                break;
            default:
                System.out.println("The input you entered is incorrect. Please try again!");
        }
    }

    /**
     * Handles returning a book
     */
    private static void handleReturnBook() {
        try {
            String id, isbn;
            Date currentDate;

            System.out.print("Please provide the ISBN number of the book you're returning: ");
            isbn = s.next().trim();
            if(Util.isInvalidISBN(isbn)) {
                System.out.println("Error: Invalid ISBN provided");
                return;
            }

            System.out.print("Please provide your UserID: ");
            id = s.next().trim();
            if(Util.isInvalidUserID(id)) {
                System.out.println("Error: Invalid UserID provided");
                return;
            }

            System.out.print("Please provide the current date: ");
            currentDate = Util.parseDateString(s.next().trim());
            long isbnLong = Util.convertISBNToLong(isbn);

            System.out.println("\nLoading...");

            boolean isLate = returnStack.pushLog(isbnLong, Util.convertIDToLong(id), currentDate, bookRepository);
            Book book = bookRepository.fetch(isbnLong);

            if(isLate) {
                System.out.println(book.getName() + " has been returned LATE! Checking everything in...");
                while(!returnStack.isEmpty()) {
                    returnStack.popLog(bookRepository);
                }
            } else {
                System.out.println(book.getName() + " has been returned on time!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles checking the last returned book
     */
    private static void handleSeeLastReturn() {
        try {
            System.out.println("\nLoading...");
            long isbn = returnStack.peekLog().getISBN();
            Book book = bookRepository.fetch(isbn);
            System.out.println(book.getName() + " is the next book to be checked in.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles checking in the last returned book
     */
    private static void handleCheckInLastReturn() {
        try {
            System.out.println("\nLoading...");
            long isbn = returnStack.popLog(bookRepository).getISBN();
            Book book = bookRepository.fetch(isbn);
            System.out.println(book.getName() + " has been checked in.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the return stack interaction
     */
    private static void handleReturnStack() {
        System.out.print("Please select an option: ");
        String input = s.next().trim();
        switch (input) {
            case "R":
                handleReturnBook();
                break;
            case "L":
                handleSeeLastReturn();
                break;
            case "C":
                handleCheckInLastReturn();
                break;
            case "P":
                System.out.print(returnStack.toString());
                break;
            default:
                System.out.println("The input you entered is incorrect. Please try again!");
        }
    }

    /**
     * Runs the menu interaction between human and program until Q is entered
     * @return Whether or not Q was entered (should program continue or not?)
     */
    private static boolean runMenu() {
        printMenu();
        String input = s.next().trim();
        switch (input) {
            case "B":
                handleBookRepo();
                break;
            case "R":
                handleReturnStack();
                break;
            case "Q":
                return false;
            default:
                System.out.println("The input you entered is incorrect. Please try again!");
        }
        return true;
    }

    public static void main(String[] args) {
        bookRepository = new BookRepository();
        returnStack = new ReturnStack();
        s = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("Starting...");
        boolean isRunning = true;
        while(isRunning) {
            isRunning = runMenu();
        }
        System.out.println("Sorry to see you go!");
    }
}
