public class BookCheckedOutBySomeoneElseException extends Exception {
    public BookCheckedOutBySomeoneElseException() {
        super();
    }

    public BookCheckedOutBySomeoneElseException(String message) {
        super(message);
    }
}
