/**
 * The {@link InvalidLineCountException} class is a custom Exception class.
 * It is thrown whenever the user attempts to add <= 0 {@link Line}s.
 */
public class InvalidLineCountException extends Exception {
    /**
     * Creates a default instance of {@link InvalidLineCountException}
     */
    public InvalidLineCountException() {
        super();
    }

    /**
     * Creates an instance of {@link InvalidLineCountException} with a message
     * @param message   The exception message
     */
    public InvalidLineCountException(String message) {
        super(message);
    }
}
