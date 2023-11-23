/**
 * A representation of a bookshelf. This is implemented as a LinkedList consisting of nodes that are books
 */
public class Shelf {
    private Book headBook;
    private Book tailBook;
    private int length;
    private SortCriteria shelfSortCriteria;
    private final int defaultPadding = 25;

    /**
     * Creates a default instance of the Shelf
     */
    public Shelf() {
        length = 0;
        shelfSortCriteria = SortCriteria.ISBN;
    }

    /**
     * Helper method that fetches a class instance of a book with a given ISBN
     * @param isbn  The given ISBN
     * @return  The book instance with that given ISBN
     */
    public Book fetch(long isbn) {
        Book cursor = headBook;
        while(cursor.getISBN() != isbn) {
            cursor = cursor.getNextBook();
        }
        return cursor;
    }

    /**
     * Helper method that returns whether this shelf is sorted
     * @return  Whether this shelf is sorted
     */
    public boolean isSorted() {
        if(headBook == null) return true;
        Book cursor = headBook;
        // Iterate through the entire list
        while(cursor.getNextBook() != null) {
            // If this current book is ever greater than the next book, than this list isn't sorted
            if(Book.compare(cursor, cursor.getNextBook(), shelfSortCriteria) > 0) {
                return false;
            }
            cursor = cursor.getNextBook();
        }
        return true;
    }

    /**
     * Check if a book with a given ISBN exists within the shelf
     * @param isbn  The given ISBN
     * @return  Whether the book exists
     */
    public boolean checkExists(long isbn) {
        Book cursor = headBook;
        while(cursor != null) {
            if(cursor.getISBN() == isbn) {
                return true;
            }
            cursor = cursor.getNextBook();
        }
        return false;
    }

    /**
     * Add a book to the shelf. To maintain sorted order, we simply just re-sort every time we add a book
     * @param addedBook The newly added book
     * @throws BookAlreadyExistsException   Thrown if that book already exists in the shelf
     */
    public void addBook(Book addedBook) throws BookAlreadyExistsException {
        if(checkExists(addedBook.getISBN())) {
            throw new BookAlreadyExistsException("Book already exists");
        }
        length++;
        if(length == 1) {
            headBook = addedBook;
            tailBook = addedBook;
        } else {
            tailBook.setNextBook(addedBook);
            tailBook = addedBook;
            sort(shelfSortCriteria);
        }
    }

    /**
     * Remove a book with a given ISBN from this shelf
     * @param removedISBN   The given ISBN
     * @throws InvalidISBNException     Thrown if the given ISBN is in an invalid format
     * @throws BookDoesNotExistException   Thrown if no book with the given ISBN exists
     */
    public void removeBook(String removedISBN) throws InvalidISBNException, BookDoesNotExistException {
        if(Util.isInvalidISBN(removedISBN)) {
            throw new InvalidISBNException();
        }
        long isbn = Util.convertISBNToLong(removedISBN);
        if(!checkExists(isbn)) {
            throw new BookDoesNotExistException();
        }

        // Iterate through the entire list, while maintaining a trailing pointer
        Book trail = null;
        Book cursor = headBook;
        while(cursor != null) {
            // If we match, then set the trailing pointer next node to be the cursor's next node
            if(cursor.getISBN() == isbn) {
                // Handle edge cases of cursor being head/tail
                if(cursor == headBook) {
                    headBook = cursor.getNextBook();
                }
                if(cursor == tailBook) {
                    tailBook = trail;
                }
                if(trail != null) {
                    trail.setNextBook(cursor.getNextBook());
                }
                return;
            }
            trail = cursor;
            cursor = cursor.getNextBook();
        }
    }

    /**
     * Sort this shelf.
     * This is done with the BubbleSort algorithm.
     * The psuedo-code is as follows;
     * while not sorted:
     *      iterate through list
     *      if cursor > cursor.next, then swap cursor and cursor.next
 *
     * The > is determined by sortCriteria
     * @param sortCriteria  The criteria on which we choose to sort
     */
    public void sort(SortCriteria sortCriteria) {
        this.shelfSortCriteria = sortCriteria;
        while(!isSorted()) {
            // Maintain a trailing and next ptr for a given cursor
            Book cursor = headBook;
            Book prev = null;
            Book nxt = cursor.getNextBook();
            while(nxt != null) {
                // If we find a bad pair
                if(Book.compare(cursor, nxt, shelfSortCriteria) > 0) {
                    // Swap cursor and nxt
                    Book nextPtr = nxt.getNextBook();
                    // Handle cases where cursor is head of list/not head of list
                    if(prev != null) {
                        prev.setNextBook(nxt);
                    } else {
                        headBook = nxt;
                    }
                    // Do the swap
                    nxt.setNextBook(cursor);
                    cursor.setNextBook(nextPtr);
                    prev = nxt;
                    nxt = cursor.getNextBook();
                } else {
                    prev = cursor;
                    cursor = nxt;
                    nxt = nxt.getNextBook();
                }
                // Set the tail if valid
                if(nxt == null) tailBook = cursor;
            }
        }
    }

    /**
     * Check in a book
     * @param isbn  Given ISBN
     * @throws BookDoesNotExistException    Thrown if book with that ISBN doesn't exist
     */
    public void checkIn(long isbn) throws BookDoesNotExistException {
        if(!checkExists(isbn)) {
            throw new BookDoesNotExistException("Error: Book does not exist");
        }
        fetch(isbn).setCheckedOut(false);
    }

    /**
     * Check out a book
     * @param isbn  The ISBN that we are checking out
     * @param checkedOutUserID  The checked out user ID
     * @param dueDate   The due date that the user should return book by
     * @param checkOutDate  The current date (or date that we are checking out)
     */
    public void checkOut(long isbn, long checkedOutUserID, Date dueDate, Date checkOutDate) {
        Book node = fetch(isbn);
        node.setCheckedOut(true);
        node.setCheckOutUserID(checkedOutUserID);
        node.setDueDate(dueDate);
        node.setCheckOutDate(checkOutDate);
    }

    /**
     * Return whether book is checked out
     * @param isbn  Given ISBN of a book
     * @return  If checked out
     */
    public boolean isCheckedOut(long isbn) {
        return fetch(isbn).isCheckedOut();
    }

    /**
     * Center a string
     * @param s Given string
     * @return  That string, centered
     */
    private String centerString(String s) {
        int rightPadding = s.length() + ((defaultPadding - s.length()) / 2);
        String leftStr = "%-" + defaultPadding + "s";
        String rightStr = "%" + rightPadding + "s";
        return String.format(leftStr, String.format(rightStr, s));
    }

    /**
     * Generate a header string for the tabular String output
     * @return  A header string
     */
    private String genHeader() {
        StringBuilder str = new StringBuilder();
        int menuWidth = defaultPadding * 4 + defaultPadding / 2;
        for(int i = 0; i < menuWidth; i++) {
            str.append("=");
            if(i == menuWidth - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Get the field that we are comparing with
     * @param book  The book that we take the field of
     * @return  The correct sorting field
     */
    private String getBooksShelfSortStringVal(Book book) {
        switch (shelfSortCriteria) {
            case CONDITION:
                return book.getBookCondition().name();
            case AUTHOR:
                return book.getAuthor();
            case GENRE:
                return book.getGenre();
            case NAME:
                return book.getName();
            case ISBN:
                return Util.convertISBNToString(book.getISBN());
        }
        return "";
    }

    /**
     * Return a tabular form of this class
     * @return  Tabular form of this class
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        // Create the header of the table
        str.append("\nLoading...\n");
        str.append(genHeader());
        String format = "| %s | %s | %s | %s |\n";
        String scString = Util.convertGoodCase(shelfSortCriteria.name());
        str.append(String.format(format, centerString(scString), centerString("Checked Out"), centerString("Checked Out Date"), centerString("Checkout UserID")));
        str.append(genHeader());
        // Iterate through the list
        Book cursor = headBook;
        while(cursor != null) {
            // Turn the important book values that we put into a table, into good, nicely formatted strings
            String scVal = centerString(getBooksShelfSortStringVal(cursor));
            // If a book isn't checked out, simply just display N/A for its respective data fields that care about being checked out
            String checkedOut = centerString(cursor.isCheckedOut() ? "Y" : "N");
            String checkOutDate = centerString(cursor.isCheckedOut() ? cursor.getCheckOutDate().toString() : "N/A");
            String checkOutUserID = centerString(cursor.isCheckedOut() ? Util.convertIDToString(cursor.getCheckOutUserID()) : "N/A");
            str.append(String.format(format, scVal, checkedOut, checkOutDate, checkOutUserID));
            cursor = cursor.getNextBook();
        }
        // Finally, add a header to the bottom of the table to clean it up nicely
        str.append(genHeader());
        return str.toString();
    }
}
