/**
 * The Book Repository class represents a collection of {@link Shelf} classes stored in an array.
 * These 10 shelf classes can be used to store every possible book.
 * A book's shelf is determined by the first significant number of its ISBN number.
 * The first significant number is the first non-zero number.
 * That number (which is 0-9) will be the index of the correct shelf
 * This class also serves to ease communication between {@link LibraryManager} and {@link Shelf}
 *
 * Note: we will refer to the ISBN's first significant digit as "FSD"
 */
public class BookRepository {
    private Shelf[] shelves;

    /**
     * Creates a default instance of the {@link BookRepository} class
     * This also initializes all the shelves to be default shelves
     */
    public BookRepository() {
        shelves = new Shelf[10];
        for(int i = 0; i < 10; i++) {
            shelves[i] = new Shelf();
        }
    }

    /**
     * Helper method to determine whether a certain book exists within the repository
     * First, we select the FSD of the ISBN
     * Then, index the shelves array for the correct shelf using the FSD
     * Now iterate through that shelf and find if any book has an ISBN corresponding to this ISBN
     * @param isbn  The queried ISBN
     * @return  Whether that ISBN exists
     */
    public boolean checkDoesNotExist(long isbn) {
        int first = Util.getISBNFirstSignificantDigit(isbn);
        return !shelves[first].checkExists(isbn);
    }

    /**
     * Fetches {@link Book} instance from somewhere in the repository. This is useful
     * when determining more information of a Book, when only given its ISBN
     * @param isbn  The queried book's ISBN
     * @return  A class instance of that queried Book
     * @throws BookDoesNotExistException    Thrown if that book does not exist within the repository
     */
    public Book fetch(long isbn) throws BookDoesNotExistException {
        if(checkDoesNotExist(isbn)) {
            throw new BookDoesNotExistException();
        }
        int first = Util.getISBNFirstSignificantDigit(isbn);
        return shelves[first].fetch(isbn);
    }

    /**
     * Check in a book to the repository system
     * @param checkedInISBN The checked in book's ISBN
     * @param checkInUserID The user that is checking in that book
     * @throws BookDoesNotExistException    Thrown if the queried book does not exist within the repository
     */
    public void checkInBook(long checkedInISBN, long checkInUserID) throws BookDoesNotExistException {
        int first = Util.getISBNFirstSignificantDigit(checkedInISBN);
        shelves[first].checkIn(checkedInISBN);
    }

    /**
     * Simulate a given user checking out a specific book from the repository
     * @param checkedOutISBN    The ISBN of the book that will be checked out
     * @param checkOutUserID    The ID of the user who is checking out the book
     * @param dueDate   The date at which the checked out book will be due
     * @param checkOutDate  The checkout date (e.g. the current date)
     * @throws InvalidISBNException Thrown if the provided ISBN is not in a valid format
     * @throws InvalidUserIDException   Thrown if the provided UserID is not in a valid format
     * @throws BookAlreadyCheckedOutException   Thrown if that queried book is already checked out
     */
    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate, Date checkOutDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyCheckedOutException {
        if(Util.isInvalidISBN(checkedOutISBN)) {
            throw new InvalidISBNException();
        }
        if(Util.IsInvalidUserID(checkOutUserID)) {
            throw new InvalidUserIDException();
        }
        int first = Util.getISBNFirstSignificantDigit(checkedOutISBN);
        if(shelves[first].isCheckedOut(checkedOutISBN)) {
            throw new BookAlreadyCheckedOutException();
        }
        shelves[first].checkOut(checkedOutISBN, checkOutUserID, dueDate, checkOutDate);
    }

    /**
     * Add a book to the repository, given the parameters needed to create a {@link Book}
     * @param addISBN   The given ISBN
     * @param addName   The given name
     * @param addAuthor The given author
     * @param addGenre  The given genre
     * @param addCondition  The given condition
     * @throws InvalidISBNException Thrown if the given ISBN is not in a valid format
     * @throws BookAlreadyExistsException   Thrown if that ISBN already exists for some book within the repository
     */
    public void addBook(long addISBN, String addName, String addAuthor, String addGenre, Condition addCondition) throws InvalidISBNException, BookAlreadyExistsException {
        if(Util.isInvalidISBN(addISBN)) {
            throw new InvalidISBNException();
        }
        int first = Util.getISBNFirstSignificantDigit(addISBN);
        shelves[first].addBook(new Book(addName, addAuthor, addGenre, addCondition, addISBN));
    }

    /**
     * Remove a book fromm the library given its ISBN
     * @param removeISBN    The queried ISBN
     * @throws InvalidISBNException Thrown if that ISBN is not of a valid format
     * @throws BookDoesNotExistException    Thrown if that given ISBN does not exist within the repository
     */
    public void removeBook(long removeISBN) throws InvalidISBNException, BookDoesNotExistException {
        if(Util.isInvalidISBN(removeISBN)) {
            throw new InvalidISBNException();
        }
        int first = Util.getISBNFirstSignificantDigit(removeISBN);
        shelves[first].removeBook(Util.convertISBNToString(removeISBN));
    }

    /**
     *
     * @param shelfInd  The index of the self chosen tos ort
     * @param sortCriteria  The criteria on which we choose to sort
     * @throws InvalidSortCriteriaException Thrown if the given string is not a valid SortCriteria
     */
    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException {
        SortCriteria sc = SortCriteria.convertStringToEnum(sortCriteria);
        shelves[shelfInd].sort(sc);
    }

    /**
     * Print a specific shelf in tabular format
     * @param shelfInd  The index of the shelf that we wish to print
     */
    public void printShelf(int shelfInd) {
        System.out.println(shelves[shelfInd].toString());
    }
}
