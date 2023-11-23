/**
 * Enum class that represents the physical condition of a book
 */
public enum Condition {
    NEW,
    GOOD,
    BAD,
    REPLACE;

    /**
     * Compares two Conditions.
     * This is done by looking at the ordinal value of the Conditions
     * An ordinal value is the position at which it was written
     * Since we are going from (GOOD CONDITION -> BAD CONDITION), we can simply just rank it by ordinal
     * @param a The first Condition to be compared
     * @param b The second condition to be compared
     * @return  An integer value whose sign represents the relative difference between a and b
     */
    public static int compare(Condition a, Condition b) {
        return Integer.compare(a.ordinal(), b.ordinal());
    }

    /**
     * Convert a String into a Condition Enum
     * @param s The given string
     * @return  The Condition Enum that String is equivalent to
     * @throws InvalidConditionException    Thrown if that String is not a Condition
     */
    public static Condition parseString(String s) throws InvalidConditionException {
        switch (s) {
            case "New":
                return Condition.NEW;
            case "Good":
                return Condition.GOOD;
            case "Bad":
                return Condition.BAD;
            case "Replace":
                return Condition.REPLACE;
        }
        throw new InvalidConditionException();
    }
}
