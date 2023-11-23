/**
 * A list of helper methods that are used throughout this program
 */
public class Util {
    // Checks if a given ISBN is valid
    public static boolean isInvalidISBN(String s) {
        if(s.length() > 13) return true;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // We always have duplicate methods for long/string when checking ISBN and ID
    public static boolean isInvalidISBN(long s) {
        return isInvalidISBN(convertISBNToString(s));
    }

    // Convert a given ISBN from a long into a String
    public static String convertISBNToString(long s) {
        // Iterate through the long by going digit by digit
        long size = 0;
        long tmp = s;
        while(tmp > 0) {
            tmp /= 10L;
            size++;
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 13 - size; i++) {
            str.append('0');
        }
        str.append(s);
        return str.toString();
    }

    // Convert a string ISBN into a LongISBN
    public static long convertISBNToLong(String s) {
        return Long.parseLong(s);
    }

    // Get the first significant (non-zero) digit of an ISBN
    public static int getISBNFirstSignificantDigit(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '0') {
                return s.charAt(i) - '0';
            }
        }
        return 0;
    }

    // Duplicate method of above, but for longs
    public static int getISBNFirstSignificantDigit(long l) {
        return getISBNFirstSignificantDigit(convertISBNToString(l));
    }

    // Check if a given UserID is valid (must be <= 10 numeric digits)
    public static boolean isInvalidUserID(String s) {
        if(s.length() > 10) return true;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Convert an ID into a String
    public static String convertIDToString(long id) {
        return String.valueOf(id);
    }

    // Convert a String ID into a Long
    public static long convertIDToLong(String id) {
        return Long.parseLong(id);
    }

    // Check if a long id is invalid
    public static boolean IsInvalidUserID(long id) {
        return isInvalidUserID(convertIDToString(id));
    }

    // Convert enum names from ALL_CAPS to Only_first_letter
    public static String convertGoodCase(String s) {
        String lower = s.toLowerCase();
        String res = lower.substring(1);
        res = Character.toUpperCase(s.charAt(0)) + res;
        return res;
    }

    // Parse a date string to create a Date class
    // If this date string is invalid, we'll throw an InvalidDateException
    public static Date parseDateString(String s) throws InvalidDateException {
        String[] components = s.split("/");
        if(components.length != 3) {
            throw new InvalidDateException("Error: Invalid Date provided");
        }
        try {
            int month = Integer.parseInt(components[0]);
            int day = Integer.parseInt(components[1]);
            int year = Integer.parseInt(components[2]);
            return new Date(day, month, year);
        } catch(Exception e) {
            throw new InvalidDateException("Error: Invalid Date provided");
        }
    }

    // Parse a shelf string to create a valid shelf index for BookRepository
    // If a shelf string is invalid, we'll throw an InvalidShelfException
    public static int parseShelfString(String s) throws InvalidShelfException {
        if(s.length() != 1 || !Character.isDigit(s.charAt(0))) {
            throw new InvalidShelfException("Error: Invalid Shelf provided");
        }
        return s.charAt(0) - '0';
    }
}
