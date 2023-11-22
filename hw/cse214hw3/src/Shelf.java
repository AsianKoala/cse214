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
    public Book fetch(long isbn) {
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
        this.shelfSortCriteria = sortCriteria;
        while(!isSorted()) {
            Book cursor = headBook;
            Book prev = null;
            Book nxt = cursor.getNextBook();
            while(nxt != null) {
                if(Book.compare(cursor, nxt, shelfSortCriteria) > 0) {
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

    // TODO: CHECK IF WE ACTUALLY SHOULD ADD A CHECKOUT DATE
    public void checkOut(long isbn, long checkedOutUserID, Date dueDate, Date checkOutDate) {
        Book node = fetch(isbn);
        node.setCheckedOut(true);
        node.setCheckOutUserID(checkedOutUserID);
        node.setDueDate(dueDate);
        node.setCheckOutDate(checkOutDate);
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

    private final int defaultPadding = 25;

    private String centerString(String s) {
        int rightPadding = s.length() + ((defaultPadding - s.length()) / 2);
        String leftStr = "%-" + defaultPadding + "s";
        String rightStr = "%" + rightPadding + "s";
        return String.format(leftStr, String.format(rightStr, s));
    }

    private void printHeader() {
        int menuWidth = defaultPadding * 4 + defaultPadding / 2;
        for(int i = 0; i < menuWidth; i++) {
            System.out.print("=");
            if(i == menuWidth - 1) {
                System.out.print("\n");
            }
        }
    }

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

    // TODO: this should be toString()
    public void printTable() {
        System.out.println("\nLoading...\n");
        printHeader();
        String format = "| %s | %s | %s | %s |\n";
        String scString = Util.convertGoodCase(shelfSortCriteria.name());
        System.out.format(format, centerString(scString), centerString("Checked Out"), centerString("Checked Out Date"), centerString("Checkout UserID"));
        printHeader();
        Book cursor = headBook;
        while(cursor != null) {
            String scVal = centerString(getBooksShelfSortStringVal(cursor));
            String checkedOut = centerString(cursor.isCheckedOut() ? "Y" : "N");
            String checkOutDate = centerString(cursor.isCheckedOut() ? cursor.getCheckOutDate().toString() : "N/A");
            String checkOutUserID = centerString(cursor.isCheckedOut() ? Util.convertIDToString(cursor.getCheckOutUserID()) : "N/A");
            System.out.format(format, scVal, checkedOut, checkOutDate, checkOutUserID);
            cursor = cursor.getNextBook();
        }
        printHeader();
    }
}
