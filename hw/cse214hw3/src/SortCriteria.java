/**
 * Enum class that represents the different types of sorting that we can do on a shelf
 */
public enum SortCriteria {
    ISBN,
    NAME,
    AUTHOR,
    GENRE,
    CONDITION,
    YEAR;

    /**
     * Converts a given string into its respective sorting criteria
     * @param s The given string
     * @return  The correct enum class
     * @throws InvalidSortCriteriaException Thrown if there is no valid sorting criteria for this string
     */
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
            case "Y":
                return SortCriteria.YEAR;
        }
        throw new InvalidSortCriteriaException();
    }
}
