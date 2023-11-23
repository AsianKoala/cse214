/**
 * Represents a Stack (implemented as a LinkedList).
 * This is a stack representation of the books that are to be returned, but not checked in yet
 * Checking in a book removes it from the stack, and you can only check in the very topmost book
 * If a book is added that is overdue, the entire stack will be checked in
 */
public class ReturnStack {
    private ReturnLog topLog;
    private final int defaultPadding = 25;

    public ReturnStack() {}

    /**
     * Adds a new ReturnLog to the stack
     * @param returnISBN    The ISBN of the book that was returned
     * @param returnUserID  The UserID of the person who returned the book
     * @param returnDate    The date on which this book was returned
     * @param bookRepoRef   A reference to the book repository (used in order to interact with the book itself)
     * @return  Whether this book was returned late. If so, clear the entire stack
     * @throws InvalidISBNException Thrown if given an invalid ISBN
     * @throws InvalidReturnDateException   Thrown if given an invalid return date (one that is before the check out date of the book)
     * @throws BookNotCheckedOutException   Thrown if the book is not checked out
     * @throws BookCheckedOutBySomeoneElseException Thrown if the book is checked out by someone else
     * @throws InvalidUserIDException   Thrown if the UserID provided is invalid
     * @throws BookDoesNotExistException    Thrown if this book does not exist in the bookRepoRef
     */
    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidUserIDException, BookDoesNotExistException {
        if(Util.isInvalidISBN(returnISBN)) {
            throw new InvalidISBNException();
        }
        if(Util.IsInvalidUserID(returnUserID)) {
            throw new InvalidUserIDException();
        }
        if(bookRepoRef.checkDoesNotExist(returnISBN)) {
            throw new BookDoesNotExistException();
        }
        Book book = bookRepoRef.fetch(returnISBN);
        if(!book.isCheckedOut()) {
            throw new BookNotCheckedOutException();
        }
        if(book.getCheckOutUserID() != returnUserID) {
            throw new BookCheckedOutBySomeoneElseException();
        }
        if(Date.compare(returnDate, book.getCheckOutDate()) <= 0) {
            throw new InvalidReturnDateException();
        }
        ReturnLog newHead = new ReturnLog(returnISBN, returnUserID, returnDate);
        newHead.setNextLog(topLog);
        topLog = newHead;

        return Date.compare(returnDate, book.getDueDate()) > 0;
    }

    /**
     * Peeks at the ReturnLog that is at the top of the stack
     * @return  Returns that ReturnLog instance
     * @throws EmptyStackException  Thrown if the stack is empty
     */
    public ReturnLog peekLog() throws EmptyStackException {
        if(topLog == null) {
            throw new EmptyStackException();
        }
        return topLog;
    }

    /**
     * Removes a book from the top of the stack, and checks it in with the book repository
     * @param repoRef A reference to a BookRepository that this book is checked into
     * @return  The book that was checked into the book repository and removed from the top of the stack
     * @throws EmptyStackException  Thrown if the stack is empty
     * @throws BookDoesNotExistException    Thrown if this book does not exist (in the case that the book was removed during the checkout)
     */
    public ReturnLog popLog(BookRepository repoRef) throws EmptyStackException, BookDoesNotExistException {
        if(topLog == null) {
            throw new EmptyStackException();
        }
        repoRef.checkInBook(topLog.getISBN(), topLog.getUserID());
        ReturnLog result = topLog;
        topLog = topLog.getNextLog();
        return result;
    }

    /**
     * Returns if the stack is empty
     * @return  If the stack is empty
     */
    public boolean isEmpty() {
        return topLog == null;
    }

    /**
     * Helper method to center a string
     * @param s Given string
     * @return That given string, but centered
     */
    private String centerString(String s) {
        int rightPadding = s.length() + ((defaultPadding - s.length()) / 2);
        String leftStr = "%-" + defaultPadding + "s";
        String rightStr = "%" + rightPadding + "s";
        return String.format(leftStr, String.format(rightStr, s));
    }

    /**
     * Header for the tabular string output
     * @return  A header for the table
     */
    private String genHeader() {
        StringBuilder str = new StringBuilder();
        int menuWidth = defaultPadding * 4 + defaultPadding / 2;
        for(int i = 0; i < menuWidth; i++) {
            str.append("=");
            if(i == menuWidth - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * String representation of this stack, in table format
     * @return  A string representation of this stack
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nLoading...\n");
        str.append(genHeader());
        String format = "| %s | %s | %s |\n";
        str.append(String.format(format, centerString("ISBN"), centerString("UserID"), centerString("Return Date")));
        str.append(genHeader());
        ReturnLog cursor = topLog;
        while(cursor != null) {
            String isbn = centerString(Util.convertISBNToString(topLog.getISBN()));
            String userID = centerString(Util.convertIDToString(topLog.getUserID()));
            String returnDate = centerString(topLog.getReturnDate().toString());
            str.append(String.format(format, isbn, userID, returnDate));
            cursor = cursor.getNextLog();
        }
        str.append(genHeader());
        return str.toString();
    }
}
