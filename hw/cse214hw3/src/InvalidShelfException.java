/**
 * Exception class that is thrown if the user provides an invalid shelf index
 */
public class InvalidShelfException extends Exception {
    public InvalidShelfException() {
        super("Error: Invalid shelf");
    }

    public InvalidShelfException(String message) {
        super(message);
    }
}
