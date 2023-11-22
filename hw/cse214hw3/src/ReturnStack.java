public class ReturnStack {
    private ReturnLog topLog;
    private final int defaultPadding = 25;

    public ReturnStack() {}

    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidUserIDException, BookDoesNotExistException {
        if(Util.isInvalidISBN(returnISBN)) {
            throw new InvalidISBNException("Error: Invalid ISBN provided");
        }
        if(Util.IsInvalidUserID(returnUserID)) {
            throw new InvalidUserIDException("Error: Invalid UserID provided");
        }
        if(bookRepoRef.checkDoesNotExist(returnISBN)) {
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
        newHead.setNextLog(topLog);
        topLog = newHead;

        return Date.compare(returnDate, book.getDueDate()) > 0;
    }

    public ReturnLog peekLog() throws EmptyStackException {
        if(topLog == null) {
            throw new EmptyStackException("Error: Return Log is empty");
        }
        return topLog;
    }

    public ReturnLog popLog(BookRepository repoRef) throws EmptyStackException {
        if(topLog == null) {
            throw new EmptyStackException("Error: Return Log is empty");
        }
        repoRef.checkInBook(topLog.getISBN(), topLog.getUserID());
        ReturnLog result = topLog;
        topLog = topLog.getNextLog();
        return result;
    }

    public boolean isEmpty() {
        return topLog == null;
    }

    private String centerString(String s) {
        int rightPadding = s.length() + ((defaultPadding - s.length()) / 2);
        String leftStr = "%-" + defaultPadding + "s";
        String rightStr = "%" + rightPadding + "s";
        return String.format(leftStr, String.format(rightStr, s));
    }

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
