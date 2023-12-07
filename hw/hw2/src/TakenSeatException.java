/**
 * The {@link TakenSeatException} class is a custom Exception class.
 * It is thrown whenever a newly added {@link Person}'s seat number already exists within one of the lines.
 */
public class TakenSeatException extends Exception {
    /**
     * Creates a default instance of {@link TakenSeatException}
     */
    public TakenSeatException() {
        super();
    }

    /**
     * Creates an instance of {@link TakenSeatException} with a message
     * @param message   The exception message
     */
    public TakenSeatException(String message) {
        super(message);
    }
}
