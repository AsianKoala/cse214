/**
 * Exception class that is thrown if an invalid ISBN is provided
 */
public class InvalidISBNException extends Exception {
    public InvalidISBNException() {
        super("Error: Invalid ISBN");
    }

    public InvalidISBNException(String message) {
        super(message);
    }
}
