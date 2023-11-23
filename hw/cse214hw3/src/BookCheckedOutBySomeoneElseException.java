/**
 * Exception class that is thrown whenever a user attempts to return a book that is currently
 * checked out by another user
 */
public class BookCheckedOutBySomeoneElseException extends Exception {
    public BookCheckedOutBySomeoneElseException() {
        super("Error: Book checked out by someone else");
    }

    public BookCheckedOutBySomeoneElseException(String message) {
        super(message);
    }
}
