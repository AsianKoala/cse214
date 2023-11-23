/**
 * The Date class represents a calendar date, (month, day, year).
 * This is used for Book check in/out, due dates, and return dates
 */
public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * Creates a default Date class instance
     */
    public Date() {
        day = 0;
        month = 0;
        year = 0;
    }

    /**
     * Create a Date class instance while specifying the data fields
     * @param day   The day
     * @param month The month
     * @param year  The year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the day this class represents
     * @return  The day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month this class represents
     * @return  The month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year this class represents
     * @return  The year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the day of this class
     * @param day   The new day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Set the month of this class
     * @param month The new month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Set the year of this class
     * @param year The new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Compares two dates, and returns an integer (in the set {-1, 0, 1})
     * that corresponds to the difference between them.
     * If A is earlier than B, then it will return -1.
     * If A is equal to B, then it will return 0.
     * If A is greater than B, then it will return 1
     * @param x The first date to be compared
     * @param y The second date to be compared
     * @return  The comparison result (in {-1, 0, 1})
     */
    public static int compare(Date x, Date y) {
        if(x.year < y.year) {
            return -1;
        } else if(y.year < x.year) {
            return 1;
        } else if(x.month < y.month) {
            return -1;
        } else if(x.month > y.month) {
            return 1;
        } else if(x.day < y.day) {
            return -1;
        } else if(x.day > y.day) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Convert this class to a String representation
     * @return String representation of this class
     */
    public String toString() {
        return String.format("%s/%s/%s", month, day, year);
    }
}
