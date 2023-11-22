public class ReturnStack {
    private ReturnLog topLog;

    public ReturnStack() {}

    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidUserIDException {
        return false;
    }

    public ReturnLog popLog() throws EmptyStackException {
        return null;
    }
}
