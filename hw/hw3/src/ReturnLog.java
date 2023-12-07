/**
 * Represents a returned book on the ReturnStack
 * This is also a LinkedList node for ReturnStack
 */
public class ReturnLog {
    private long ISBN;
    private long userID;
    private Date returnDate;
    private ReturnLog nextLog;

    /**
     * Creates a default instance of the ReturnLog class
     */
    public ReturnLog() {
        ISBN = 0;
        userID = 0;
        returnDate = new Date();
    }

    /**
     * Creates an instance of the ReturnLog class with specified parameters
     * @param ISBN  The ISBN of the returned book
     * @param userID    The UserID of the returned book
     * @param returnDate    The date that the book was returned
     */
    public ReturnLog(long ISBN, long userID, Date returnDate) {
        this.ISBN = ISBN;
        this.userID = userID;
        this.returnDate = returnDate;
    }

    /**
     * Returns the ISBN of the book
     * @return  ISBN of book
     */
    public long getISBN() {
        return ISBN;
    }

    /**
     * Returns the UserID of the person who returned the book
     * @return  UserID of this return
     */
    public long getUserID() {
        return userID;
    }

    /**
     * Returns the date at which this book was returned
     * @return  The return date of this book
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Returns the next ReturnLog in the ReturnStack
     * This is a LinkedList method
     * @return  The next ReturnLog
     */
    public ReturnLog getNextLog() {
        return nextLog;
    }

    /**
     * Sets the ISBN of this ReturnLog
     * @param ISBN
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the UserID of who returned this book
     * @param userID    The new UserID
     */
    public void setUserID(long userID) {
        this.userID = userID;
    }

    /**
     * Set the date at which this book was returned
     * @param returnDate    The new return date
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Set the next ReturnLog
     * @param nextLog   The new ReturnLog that is next in the ReturnStack
     */
    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }
}
