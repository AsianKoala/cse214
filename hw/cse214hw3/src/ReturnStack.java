public class ReturnStack {
    private ReturnLog topLog;

    public ReturnStack() {}

    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidUserIDException, BookDoesNotExistException {
        if(!Util.isValidISBN(returnISBN)) {
            throw new InvalidISBNException("Error: Invalid ISBN provided");
        }
        if(!Util.isValidUserID(returnUserID)) {
            throw new InvalidUserIDException("Error: Invalid UserID provided");
        }
        if(!bookRepoRef.checkExists(returnISBN)) {
            throw new BookDoesNotExistException("Error: Book with given ISBN does not exist");
        }
        Book book = bookRepoRef.fetch(returnISBN);
        if(!book.isCheckedOut()) {
            throw new BookNotCheckedOutException("Error: Book is not checked out");
        }
        if(book.getCheckOutUserID() != returnUserID) {
            throw new InvalidUserIDException("Error: Book checked out by another user");
        }
        if(Date.compare(returnDate, book.getCheckOutDate()) <= 0) {
            throw new InvalidReturnDateException("Error: Invalid Return Date");
        }
        ReturnLog newHead = new ReturnLog(returnISBN, returnUserID, returnDate);
        bookRepoRef.checkInBook(returnISBN, returnUserID);
        newHead.setNextLog(topLog);
        topLog = newHead;
        return Date.compare(returnDate, book.getDueDate()) <= 0;
    }

    public ReturnLog popLog() throws EmptyStackException {
        if(topLog == null) {
            throw new EmptyStackException("Error: Return Log is empty");
        }
        ReturnLog result = topLog;
        topLog = topLog.getNextLog();
        return result;
    }
}
