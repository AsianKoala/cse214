public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

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
}
