public class Main {
    public static void main(String[] args) {
        try {
//            shelfTesting();
//            isbnTesting();
            repoTesting();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void shelfTesting() throws BookAlreadyExistsException, InvalidISBNException, BookDoesNotExistException {
        Shelf s = new Shelf();
        for(int i = 1; i < 5; i++) s.addBook(new Book(i));;
        s.print();
        s.removeBook("3");
        s.print();
        s.removeBook("1");
        s.print();
    }

    private static void isbnTesting() {
        long test = 123456789012L;
        String isbn = Util.convertISBNToString(test);
        System.out.println(Util.isValidISBN(isbn));
        System.out.println(isbn);
        long test2 = 213456789;
        isbn = Util.convertISBNToString(test2);
        System.out.println(isbn);
        System.out.println(Util.getISBNFirstSignificantDigit(test2));
        System.out.println(Util.getISBNFirstSignificantDigit(isbn));
    }

    private static void repoTesting() throws InvalidISBNException, BookAlreadyExistsException, InvalidSortCriteriaException {
        BookRepository repo = new BookRepository();
        repo.addBook(1234L, "meow", "z", "y", Condition.NEW);
        repo.addBook(1333L, "aaaa", "y", "z", Condition.BAD);
        repo.shelves[1].print();
        repo.sortShelf(1, "N");
        repo.shelves[1].print();
    }
}