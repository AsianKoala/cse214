public class BookRepository {
    // TODO: make this privatae
    public Shelf[] shelves;

    public BookRepository() {
        shelves = new Shelf[10];
        for(int i = 0; i < 10; i++) {
            shelves[i] = new Shelf();
        }
    }

    public boolean checkExists(long isbn) {
        int first = Util.getISBNFirstSignificantDigit(isbn);
        return shelves[first].checkExists(isbn);
    }

    public Book fetch(long isbn) {
        int first = Util.getISBNFirstSignificantDigit(isbn);
        return shelves[first].fetch(isbn);
    }

    // TODO: why does this have checkInUserID lol?
    public void checkInBook(long checkedInISBN, long checkInUserID) {
        int first = Util.getISBNFirstSignificantDigit(checkedInISBN);
        shelves[first].checkIn(checkedInISBN);
    }

    // TODO: FIGURE OUT WTF DUEDATE AND CHECKOUT DATE ARE SUPPOSED TO DO???????/
    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate, Date checkOutDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyCheckedOutException {
        if(!Util.isValidISBN(checkedOutISBN)) {
            throw new InvalidISBNException();
        }
        if(!Util.isValidUserID(checkOutUserID)) {
            throw new InvalidUserIDException();
        }
        int first = Util.getISBNFirstSignificantDigit(checkedOutISBN);
        if(shelves[first].isCheckedOut(checkedOutISBN)) {
            throw new BookAlreadyCheckedOutException();
        }
        shelves[first].checkOut(checkedOutISBN, checkOutUserID, dueDate, checkOutDate);
    }

    public void addBook(long addISBN, String addName, String addAuthor, String addGenre, Condition addCondition) throws InvalidISBNException, BookAlreadyExistsException {
        if(!Util.isValidISBN(addISBN)) {
            throw new InvalidISBNException();
        }
        int first = Util.getISBNFirstSignificantDigit(addISBN);
        shelves[first].addBook(new Book(addName, addAuthor, addGenre, addCondition, addISBN));
    }

    public void removeBook(long removeISBN) throws InvalidISBNException, BookDoesNotExistException {
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

    public void printShelf(int shelfInd) {
        shelves[shelfInd].printTable();
    }
}
