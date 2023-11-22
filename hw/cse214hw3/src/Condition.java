public enum Condition {
    NEW,
    GOOD,
    BAD,
    REPLACE;

    public static int compare(Condition a, Condition b) {
        return Integer.compare(a.ordinal(), b.ordinal());
    }

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
