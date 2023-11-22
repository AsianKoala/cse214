public class BookNotCheckedOutException extends Exception {
    public BookNotCheckedOutException() {
        super();
    }

    public BookNotCheckedOutException(String message) {
        super(message);
    }
}
