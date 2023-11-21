public class Shelf {
    private Book headBook;
    private Book tailBook;
    private int length;
    private SortCriteria shelfSortCriteria;

    public Shelf() {
        length = 0;
        shelfSortCriteria = SortCriteria.ISBN;
    }

    // Helper method to insert books
    private void insert(Book book) {
        if(length == 0) {
            headBook = book;
            tailBook = book;
        } else {
            Book cursor = headBook;
            Book trail = null;
            boolean foundBook = false;
            while(cursor != null) {
            }
        }
        length++;
    }

    public void addBook(Book addedBook) throws BookAlreadyExistsException {

    }

    public void removeBook(String removedISBN) throws InvalidISBNException, BookDoesNotExistException{

    }

    public void sort(SortCriteria sortCriteria) {

    }
}
