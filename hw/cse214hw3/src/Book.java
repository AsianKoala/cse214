public class Book {
    private String name;
    private String author;
    private String genre;
    private Condition bookCondition;
    private long ISBN;
    private long checkOutUserID;
    private int yearPublished;
    private Date checkOutDate;
    private Book nextBook;
    private boolean checkedOut;

    public Book() {
        name = "";
        author = "";
        genre = "";
        bookCondition = Condition.GOOD;
        ISBN = 0;
        checkOutUserID = 0;
        yearPublished = 0;
        checkOutDate = new Date();
        nextBook = null;
        checkedOut = false;
    }

    public Book(String name, String author, String genre, Condition bookCondition, long ISBN, long checkOutUserID, int yearPublished, Date checkOutDate, boolean checkedOut) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookCondition = bookCondition;
        this.ISBN = ISBN;
        this.checkOutUserID = checkOutUserID;
        this.yearPublished = yearPublished;
        this.checkOutDate = checkOutDate;
        this.checkedOut = checkedOut;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Condition getBookCondition() {
        return bookCondition;
    }

    public long getISBN() {
        return ISBN;
    }

    public long getCheckOutUserID() {
        return checkOutUserID;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Book getNextBook() {
        return nextBook;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setBookCondition(Condition bookCondition) {
        this.bookCondition = bookCondition;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setCheckOutUserID(long checkOutUserID) {
        this.checkOutUserID = checkOutUserID;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setNextBook(Book nextBook) {
        this.nextBook = nextBook;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public static boolean isGreater(Book a, Book b, SortCriteria c) {
        switch (c) {
            case ISBN:
                return a.getISBN() > b.getISBN();
            case NAME:
                return a.getName().compareTo(b.getName()) >= 0;
        }
    }
}
