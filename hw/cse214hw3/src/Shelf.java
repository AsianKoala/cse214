public class Shelf {
    private Book headBook;
    private Book tailBook;
    private int length;
    private SortCriteria shelfSortCriteria;

    public Shelf() {
        length = 0;
        shelfSortCriteria = SortCriteria.ISBN;
    }

    // pre-condition: isbn exists within shelf
    private Book fetch(long isbn) {
        Book cursor = headBook;
        while(cursor.getISBN() != isbn) {
            cursor = cursor.getNextBook();
        }
        return cursor;
    }

    public boolean isSorted() {
        if(headBook == null) return true;
        Book cursor = headBook;
        while(cursor.getNextBook() != null) {
            if(Book.compare(cursor, cursor.getNextBook(), shelfSortCriteria) > 0) {
                return false;
            }
            cursor = cursor.getNextBook();
        }
        return true;
    }

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


    // TODO: CHECK THAT IT SHOULD BE A LONG?
    // TODO: in doc it said it should be a String
    public void removeBook(String removedISBN) throws InvalidISBNException, BookDoesNotExistException {
        if(!Util.isValidISBN(removedISBN)) {
            throw new InvalidISBNException();
        }
        long isbn = Util.convertISBNToLong(removedISBN);
        if(!checkExists(isbn)) {
            throw new BookDoesNotExistException();
        }
        Book trail = null;
        Book cursor = headBook;
        while(cursor != null) {
            if(cursor.getISBN() == isbn) {
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

    public void sort(SortCriteria sortCriteria) {
        while(!isSorted()) {
            Book cursor = headBook;
            Book prev = null;
            Book nxt = cursor.getNextBook();
            while(nxt != null) {
                if(Book.compare(cursor, nxt, sortCriteria) > 0) {
                    if(prev != null) {
                        Book sig = nxt.getNextBook();
                        prev.setNextBook(nxt);
                        nxt.setNextBook(cursor);
                        cursor.setNextBook(sig);
                    } else {
                        Book sig = nxt.getNextBook();
                        headBook = nxt;
                        nxt.setNextBook(cursor);
                        cursor.setNextBook(sig);
                    }
                    prev = nxt;
                    nxt = cursor.getNextBook();
                    if(nxt == null) tailBook = cursor;
                } else {
                    prev = cursor;
                    cursor = nxt;
                    nxt = nxt.getNextBook();
                    if(nxt == null) tailBook = cursor;
                }
            }
        }
    }

    public int getLength() {
        return length;
    }

    public SortCriteria getShelfSortCriteria() {
        return shelfSortCriteria;
    }

    public void setShelfSortCriteria(SortCriteria shelfSortCriteria) {
        this.shelfSortCriteria = shelfSortCriteria;
    }

    public void checkIn(long isbn) {
        if(!checkExists(isbn)) return;
        fetch(isbn).setCheckedOut(false);
    }

    public void checkOut(long isbn, long checkedOutUserID, Date dueDate) {
        Book node = fetch(isbn);
        node.setCheckedOut(true);
        node.setCheckOutUserID(checkedOutUserID);
        node.setCheckOutDate(dueDate);
    }

    public boolean isCheckedOut(long isbn) {
        return fetch(isbn).isCheckedOut();
    }

    public void print() {
        System.out.println("L: " + length);
        Book cursor = headBook;
        while(cursor != null) {
            System.out.println(cursor.getISBN());
            cursor = cursor.getNextBook();
        }
    }
}
