public class BookRepository {
    Shelf[] shelves;

    public BookRepository() {
        shelves = new Shelf[10];
    }

    public boolean checkExists(long isbn) {
        int first = Util.getISBNFirstSignificantDigit(isbn);
        return shelves[first].checkExists(isbn);
    }

    public void checkInBook(long checkedInISBN, long checkInUserID) {
        int first = Util.getISBNFirstSignificantDigit(checkedInISBN);
    }

    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyExistsException {
        if(!Util.isValidISBN(checkedOutISBN)) {
            throw new InvalidISBNException();
        }
        if(!Util.us)
        int first = Util.getISBNFirstSignificantDigit(checkedOutISBN);
    }

    public void addBook(long addISBN, String addName, String addAuthor, String addGenre, Condition addCondition) throws InvalidISBNException, BookAlreadyExistsException {
        if(!Util.isValidISBN(addISBN)) {
            throw new InvalidISBNException();
        }
        int first = Util.getISBNFirstSignificantDigit(addISBN);
        shelves[first].addBook(new Book(addName, addAuthor, addGenre, addCondition, addISBN));
    }

    public void removeBook(long removeISBN) throws InvalidISBNException {
        if(!Util.isValidISBN(removeISBN)) {
            throw new InvalidISBNException();
        }
        int first = Util.getISBNFirstSignificantDigit(removeISBN);
        shelves[first].removeBook(Util.convertISBNToString(removeISBN));
    }

    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException {
        SortCriteria sc = SortCriteria.convertStringToEnum(sortCriteria);
        shelves[shelfInd].sort(sc);
    }
}
