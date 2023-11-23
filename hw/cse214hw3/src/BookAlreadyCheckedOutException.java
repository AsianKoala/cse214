/**
 * Exception class that is thrown whenever a user attempts to check out a book that is already checked out
 */
public class BookAlreadyCheckedOutException extends Exception {
    public BookAlreadyCheckedOutException() {
        super("Error: Book already checked out");
    }

    public BookAlreadyCheckedOutException(String message) {
        super(message);
    }
}
