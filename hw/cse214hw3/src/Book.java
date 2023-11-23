/**
 * The {@link Book} class represents a Book in the Library. This class is also used as
 * a LinkedList node for the {@link Shelf} class
 */
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

    /**
     * Default Constructor for the Book class. Initializes fields with default values
     */
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
    /**
     * The normal constructor for the Book class. Accepts values that are needed for Books that are stored
     * within the Library
     * @param name
     * @param author
     * @param genre
     * @param bookCondition
     * @param ISBN
     */
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

    /**
     * Returns the name of this book
     * @return  This book's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the author of this book
     * @return  This book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the genre of this book
     * @return  This book's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the condition of this book
     * @return  This book's condition
     */
    public Condition getBookCondition() {
        return bookCondition;
    }

    /**
     * Returns the ISBN of this book
     * @return  This book's ISBN
     */
    public long getISBN() {
        return ISBN;
    }

    /**
     * Returns the ID of the last user who checked out this book
     * @return  ID of the last user who checked out this book
     */
    public long getCheckOutUserID() {
        return checkOutUserID;
    }

    /**
     * Returns the year this book was published
     * @return  The year this book was published
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Returns the date that this book was last checked out on
     * @return  The last checkout date of this book
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns the due date of this book
     * @return  The last assigned due date of this book
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Returns the next book.
     * This is a LinkedList function
     * @return  Pointer to the next book
     */
    public Book getNextBook() {
        return nextBook;
    }

    /**
     * Returns whether this book is currently checked out by a user
     * @return  Whether this book is checked out
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * Sets the name of the book
     * @param name  The new name of this book
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the author of this book
     * @param author    The new author of this book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the genre of this book
     * @param genre     The new genre of this book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets the condition of this book
     * @param bookCondition The new condition of this book
     */
    public void setBookCondition(Condition bookCondition) {
        this.bookCondition = bookCondition;
    }

    /**
     * Sets the ISBN of this book
     * @param ISBN  The new ISBN of this book
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the ID of the person who last checked out this book
     * @param checkOutUserID    The new ID
     */
    public void setCheckOutUserID(long checkOutUserID) {
        this.checkOutUserID = checkOutUserID;
    }

    /**
     * Sets the year published of this book
     * @param yearPublished The new year published
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * Sets the checkout date of this book
     * @param checkOutDate  The new checkout date
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Set the due date of this book
     * @param dueDate   The new due date of this book
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets the book that directly precedes this one in a shelf
     * This is a LinkedList function
     * @param nextBook  The
     */
    public void setNextBook(Book nextBook) {
        this.nextBook = nextBook;
    }

    /**
     * Sets whether this book is currently cheked out by a user
     * @param checkedOut    Whether this book is checked out
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * Compares two books, and returns a value that represents their comparison.
     * If compare(a, b) returns -1, that means a is strictly smaller than b (a < b)
     * If compare(a, b) returns 1, that means a is strictly greater than b (a > b)
     * If compare(a, b) returns 0, that means a == b
     * The "equality" is determined by comparing two fields. This is represented
     * by the SortCriteria Enum.
     * The valid fields are:
     * ISBN
     * Name
     * Genre
     * Author
     * Condition
     * Year
     * @param a The first book to compare
     * @param b The second book to compare
     * @param c The criteria on which the first and second book will be compared
     * @return  The comparison result between a and b (-1, 0, or 1)
     */
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
