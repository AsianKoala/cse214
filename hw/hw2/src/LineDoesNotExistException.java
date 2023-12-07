/**
 * The {@link LineDoesNotExistException} class is a custom Exception class.
 * It is thrown whenever the user attempts to remove a {@link Line} that does not exist
 */
public class LineDoesNotExistException extends Exception {
    /**
     * Creates a default instance of {@link LineDoesNotExistException}
     */
    public LineDoesNotExistException() {
        super();
    }

    /**
     * Creates an instance of {@link LineDoesNotExistException} with a message
     * @param message   The exception message
     */
    public LineDoesNotExistException(String message) {
        super(message);
    }
}
