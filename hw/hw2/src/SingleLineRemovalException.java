/**
 * The {@link SingleLineRemovalException} class is a custom Exception class.
 * It is thrown whenever the user attempts to remove the last {@link Line}
 */
public class SingleLineRemovalException extends Exception {
    /**
     * Creates a default instance of {@link SingleLineRemovalException}
     */
    public SingleLineRemovalException() {
        super();
    }

    /**
     * Creates an instance of {@link SingleLineRemovalException} with a message
     * @param message   The exception message
     */
    public SingleLineRemovalException(String message) {
        super(message);
    }
}
