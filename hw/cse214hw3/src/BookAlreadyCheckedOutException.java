public class BookAlreadyCheckedOutException extends Exception {
    public BookAlreadyCheckedOutException() {
        super();
    }

    public BookAlreadyCheckedOutException(String message) {
        super(message);
    }
}
