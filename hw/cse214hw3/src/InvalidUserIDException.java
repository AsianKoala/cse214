/**
 * Exception class that is thrown if the user provides an invalid UserID
 */
public class InvalidUserIDException extends Exception {
    public InvalidUserIDException() {
        super("Error: Invalid UserID");
    }

    public InvalidUserIDException(String message) {
        super(message);
    }
}
