/**
 * Exception clas that is thrown if the return date is earlier than the checkout date
 */
public class InvalidReturnDateException extends Exception {
    public InvalidReturnDateException() {
        super("Error: Invalid Return date");
    }

    public InvalidReturnDateException(String message) {
        super(message);
    }
}
