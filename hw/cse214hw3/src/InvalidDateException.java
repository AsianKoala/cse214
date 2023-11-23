/**
 * Exception class that is thrown if a date string is unable to be parsed
 */
public class InvalidDateException extends Exception {
    public InvalidDateException() {
        super("Error: Invalid Date");
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
