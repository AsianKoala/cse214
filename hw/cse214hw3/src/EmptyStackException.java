/**
 * Exception class that is thrown if the Return Log is queried to clear, when the Return Log is emptied
 */
public class EmptyStackException extends Exception {
    public EmptyStackException() {
        super("Error: Return Log is empty");
    }

    public EmptyStackException(String message) {
        super(message);
    }
}
