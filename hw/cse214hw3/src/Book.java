public class Book {
    private String name;
    private String author;
    private String genre;
    private Condition bookCondition;
    private long ISBN;
    private long checkOutUserID;
    private int yearPublished;
    private Date checkOutDate;
    private Date dueDate;
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
        dueDate = new Date();
        checkedOut = false;
        nextBook = null;
    }

    public Book(long ISBN) {
        this.ISBN = ISBN;
        name = "";
        author = "";
        genre = "";
        bookCondition = Condition.GOOD;
        checkOutUserID = 0;
        yearPublished = 0;
        checkOutDate = new Date();
        dueDate = new Date();
        checkedOut = false;
        nextBook = null;
    }

    public Book(String name, String author, String genre, Condition bookCondition, long ISBN) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookCondition = bookCondition;
        this.ISBN = ISBN;
        checkOutUserID = 0;
        yearPublished = 0;
        checkOutDate = new Date();
        dueDate = new Date();
        checkedOut = false;
        nextBook = null;
    }

    public Book(String name, String author, String genre, Condition bookCondition, long ISBN, long checkOutUserID, int yearPublished, Date checkOutDate, Date dueDate, boolean checkedOut) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookCondition = bookCondition;
        this.ISBN = ISBN;
        this.checkOutUserID = checkOutUserID;
        this.yearPublished = yearPublished;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.checkedOut = checkedOut;
        this.nextBook = null;
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

    public Date getDueDate() {
        return dueDate;
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

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setNextBook(Book nextBook) {
        this.nextBook = nextBook;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    // pos if a > b, 0 of a == b, neg if a < b
    public static int compare(Book a, Book b, SortCriteria c) {
        switch (c) {
            case ISBN:
                return Long.compare(a.getISBN(), b.getISBN());
            case NAME:
                return a.getName().compareTo(b.getName());
            case GENRE:
                return a.getGenre().compareTo(b.getGenre());
            case AUTHOR:
                return a.getAuthor().compareTo(b.getAuthor());
            case CONDITION:
                return Condition.compare(a.getBookCondition(), b.getBookCondition());
        }
        return 0;
    }
}
