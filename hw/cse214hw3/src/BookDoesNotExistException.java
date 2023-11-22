public class BookDoesNotExistException extends Exception {
    public BookDoesNotExistException() {
        super();
    }

    public BookDoesNotExistException(String message) {
        super(message);
    }
}
