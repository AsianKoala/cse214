/**
 * The {@link AllLinesEmptyException} class is a custom Exception class.
 * It is thrown if all {@link Line}s are empty when the next {@link Person} is requested
 */
public class AllLinesEmptyException extends Exception {
    /**
     * Creates a default instance of {@link AllLinesEmptyException}
     */
    public AllLinesEmptyException() {
        super();
    }

    /**
     * Creates an instance of {@link AllLinesEmptyException} with a message
     * @param message   The exception message
     */
    public AllLinesEmptyException(String message) {
        super(message);
    }
}
