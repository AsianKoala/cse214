public class Main {
    public static void main(String[] args) {
        try {
            shelfTesting();
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
    }
}