public class BookRepository {
    Shelf[] shelves;

    public BookRepository() {
        shelves = new Shelf[10];
    }

    public void checkInBook(int checkedInISBN, int checkInUserID) {

    }

    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyExistsException {

    }

    public void addBook(int addISBN, String addName, String addAuthor, String addGenre, Condition addCondition) throws InvalidISBNException, BookAlreadyExistsException {

    }

    public void removeBook(long removeISBN) throws InvalidISBNException {

    }

    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException {

    }
}
