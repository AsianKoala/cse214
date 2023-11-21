public class ReturnLog {
    private long ISBN;
    private long userID;
    private Date returnDate;
    private ReturnLog nextLog;

    public ReturnLog() {
        ISBN = 0;
        userID = 0;
        returnDate = new Date();
    }

    public ReturnLog(long ISBN, long userID, Date returnDate) {
        this.ISBN = ISBN;
        this.userID = userID;
        this.returnDate = returnDate;
    }

    public long getISBN() {
        return ISBN;
    }

    public long getUserID() {
        return userID;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public ReturnLog getNextLog() {
        return nextLog;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }
}
