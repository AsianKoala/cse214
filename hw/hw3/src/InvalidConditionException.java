/**
 * Exception class that is thrown if a given Condition string is unable to be parsed
 */
public class InvalidConditionException extends Exception {
    public InvalidConditionException() {
        super("Error: Invalid condition");
    }

    public InvalidConditionException(String message) {
        super(message);
    }
}
