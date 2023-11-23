/**
 * Exception class that is thrown whenever a user attempts to add a new book that already exists within the library
 * "Exists" is determined by ISBN number
 */
public class BookAlreadyExistsException extends Exception {
    public BookAlreadyExistsException() {
        super("Error: Book already exists");
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
