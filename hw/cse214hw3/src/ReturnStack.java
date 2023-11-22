public class ReturnStack {
    private ReturnLog topLog;
    private int length = 0;
    public ReturnStack() {}

    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidUserIDException {
        return false;
    }

    public ReturnLog popLog() {
        return null;
    }
}
