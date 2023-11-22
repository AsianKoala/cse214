public enum SortCriteria {
    ISBN,
    NAME,
    AUTHOR,
    GENRE,
    CONDITION;

    public static SortCriteria convertStringToEnum(String s) throws InvalidSortCriteriaException {
        switch(s) {
            case "I":
                return SortCriteria.ISBN;
            case "N":
                return SortCriteria.NAME;
            case "A":
                return SortCriteria.AUTHOR;
            case "G":
                return SortCriteria.GENRE;
            case "C":
                return SortCriteria.CONDITION;
        }
        throw new InvalidSortCriteriaException();
    }
}
