public enum Condition {
    NEW,
    GOOD,
    BAD,
    REPLACE;

    public static int compare(Condition a, Condition b) {
        return Integer.compare(a.ordinal(), b.ordinal());
    }
}
