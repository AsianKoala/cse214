import java.util.Scanner;

public class LibraryManager {
    private static BookRepository bookRepository;
    private static ReturnStack returnStack;
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
            System.out.println("Loading...");
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

            bookRepository.checkOutBook(Util.convertISBNToLong(isbn), Util.convertIDToLong(id), dueDate, checkoutDate);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleAddNewBook() {
        try {
            System.out.println("Loading...");
            long isbn;
            String name, author, genre, condStr;
            Condition cond;

            System.out.print("Please provide an ISBN number: ");
            String input = s.next().trim();
            if(Util.isInvalidISBN(input)) {
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

            bookRepository.addBook(isbn, name, author, genre, cond);

            // TODO ADD SUCCESS STATEMENTS
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleRemoveBook() {
        try {
            System.out.println("Loading...");
            String isbn;
            System.out.print("Please provide an ISBN number: ");
            isbn = s.next().trim();
            if(Util.isInvalidISBN(isbn)) {
                throw new InvalidISBNException("Error: Invalid ISBN provided");
            }
            bookRepository.removeBook(Util.convertISBNToLong(isbn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handlePrintRepo() {
        try {
            System.out.println("Loading...");
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
            System.out.println("Loading...");
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

    private static void handleReturnBook() {
        try {
            System.out.println("Loading...");
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

    private static void handleSeeLastReturn() {
        try {
            System.out.println("Loading...");
            long isbn = returnStack.peekLog().getISBN();
            Book book = bookRepository.fetch(isbn);
            System.out.println(book.getName() + " is the next book to be checked in.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleCheckInLastReturn() {
        try {
            System.out.println("Loading...");
            long isbn = returnStack.popLog(bookRepository).getISBN();
            Book book = bookRepository.fetch(isbn);
            System.out.println(book.getName() + " has been checked in.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
            default:
                System.out.println("The input you entered is incorrect. Please try again!");
        }
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
