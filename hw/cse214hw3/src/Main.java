public class Main {
    public static void main(String[] args) {
        try {
//            shelfTesting();
//            isbnTesting();
//            repoTesting();
//            dateTesting();
            testRandom();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void shelfTesting() throws BookAlreadyExistsException, InvalidISBNException, BookDoesNotExistException {
        Shelf s = new Shelf();
        for(int i = 1; i < 5; i++) s.addBook(new Book(i));;
        s.removeBook("3");
        s.printTable();
        s.checkOut(1L, 1234567890L, new Date(31, 8, 2004), new Date(30, 8, 2004));
        s.printTable();
        s.checkIn(1L);
        s.printTable();
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

    private static void dateTesting() {
        Date d1 = new Date(30, 8, 2004);
        Date d2 = new Date(31, 8, 2004);
        System.out.println(Date.compare(d1, d2));
    }

    private static void testRandom() {
//        String s = "NAME";
//        String good = Util.convertGoodCase(s);
//        System.out.println(good);
//        Character c = '9';
//        int val = c - '0';
//        System.out.println(val);
        String a = "morean";
        String b = "neil";
        System.out.println(a.compareTo(b));
    }
}