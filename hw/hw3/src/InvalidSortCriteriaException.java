/**
 * Exception class that is thrown if the user provides an invalid sort criteria
 */
public class InvalidSortCriteriaException extends Exception {
    public InvalidSortCriteriaException() {
        super("Error: Invalid sort criteria");
    }

    public InvalidSortCriteriaException(String message) {
        super(message);
    }
}
