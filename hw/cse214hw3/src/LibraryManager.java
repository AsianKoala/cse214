import java.util.Scanner;

public class LibraryManager {
    private static BookRepository bookRepository;
    private static Scanner s;

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
        System.out.println("\t (S) - See Last Return");
        System.out.println("\t (C) - Check In Last Return");
        System.out.println("\t (P) - Print Return Stack");
        System.out.println("(Q) - Quit");
        System.out.println();
        System.out.print("Please select what to manage: ");
    }

    private static void handleCheckoutBook() {
        try {
            String id, isbn;
            Date dueDate, checkoutDate;

            System.out.print("Please provide a library user ID: ");
            id = s.next().trim();
            if(!Util.isValidUserID(id)) {
                System.out.println("Error: Invalid UserID provided");
                return;
            }

            System.out.print("Please provide an ISBN number: ");
            isbn = s.next().trim();
            if(!Util.isValidISBN(isbn)) {
                System.out.println("Error: Invalid ISBN provided");
                return;
            }

            System.out.print("Please provide a due date: ");
            dueDate = Util.parseDateString(s.next().trim());

            System.out.print("Please provide the checkout date (current date): ");
            checkoutDate = Util.parseDateString(s.next().trim());

            System.out.println("Loading...");
            bookRepository.checkOutBook(Util.convertISBNToLong(isbn), Util.convertIDToLong(id), dueDate, checkoutDate);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAddNewBook() {
        try {
            long isbn;
            String name, author, genre, condStr;
            Condition cond;

            System.out.print("Please provide an ISBN number: ");
            String input = s.next().trim();
            if(!Util.isValidISBN(input)) {
                throw new InvalidISBNException("Error: Invalid ISBN provided");
            }
            isbn = Util.convertISBNToLong(input);

            System.out.print("Please provide a name: ");
            name = s.next().trim();

            System.out.print("Please provide an author");
            author = s.next().trim();

            System.out.print("Please provide a genre: ");
            genre = s.next().trim();

            System.out.print("Please provide a condition: ");
            condStr = s.next().trim();
            cond = Condition.parseString(condStr);

            System.out.println("Loading...");
            bookRepository.addBook(isbn, name, author, genre, cond);

            // TODO ADD SUCCESS STATEMENTS
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleRemoveBook() {
        try {
            String isbn;
            System.out.print("Please provide an ISBN number: ");
            isbn = s.next().trim();
            if(!Util.isValidISBN(isbn)) {
                throw new InvalidISBNException("Error: Invalid ISBN provided");
            }
            bookRepository.removeBook(Util.convertISBNToLong(isbn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handlePrintRepo() {
        try {
            String shelf;
            System.out.print("Please select a shelf: ");
            shelf = s.next().trim();
            int shelfInt = Util.parseShelfString(shelf);
            bookRepository.printShelf(shelfInt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleSortShelf() {
        try {
            String shelf, sc;
            System.out.print("Please select a shelf: ");
            shelf = s.next().trim();
            System.out.print("Please select a sorting criteria: ");
            sc = s.next().trim();
            int shelfInd = Util.parseShelfString(shelf);
            bookRepository.sortShelf(shelfInd, sc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

    private static void handleReturnStack() {
    }

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
        s = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("Starting...");
        boolean isRunning = true;
        while(isRunning) {
            isRunning = runMenu();
        }
        System.out.println("Sorry to see you go!");
    }
}
