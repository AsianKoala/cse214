/**
 * Exception class that is thrown whenever a user attempts to interact with a book that does not exist
 * within the library. Queries are made via ISBN number, so that book's ISBN number is what determines whether or not
 * it exists in the library
 */
public class BookDoesNotExistException extends Exception {
    public BookDoesNotExistException() {
        super("Error: Book does not exist");
    }

    public BookDoesNotExistException(String message) {
        super(message);
    }
}
