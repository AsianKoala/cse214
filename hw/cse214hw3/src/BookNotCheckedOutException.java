/**
 * Exception class that is thrown if a user attempts to check in a book that is not checked out
 */
public class BookNotCheckedOutException extends Exception {
    public BookNotCheckedOutException() {
        super("Error: Book not checked out");
    }

    public BookNotCheckedOutException(String message) {
        super(message);
    }
}
