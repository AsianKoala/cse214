public class Util {
    // MUST BE 13 DIGITS!
    public static boolean isInvalidISBN(String s) {
        if(s.length() > 13) return true;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInvalidISBN(long s) {
        return isInvalidISBN(convertISBNToString(s));
    }

    public static String convertISBNToString(long s) {
        long size = 0;
        long tmp = s;
        while(tmp > 0) {
            tmp /= 10;
            size++;
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 13 - size; i++) {
            str.append('0');
        }
        str.append(s);
        return str.toString();
    }

    public static long convertISBNToLong(String s) {
        return Long.parseLong(s);
    }

    public static int getISBNFirstSignificantDigit(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '0') {
                return s.charAt(i) - '0';
            }
        }
        return 0;
    }

    public static int getISBNFirstSignificantDigit(long l) {
        return getISBNFirstSignificantDigit(convertISBNToString(l));
    }

    public static boolean isInvalidUserID(String s) {
        if(s.length() > 10) return true;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String convertIDToString(long id) {
        return String.valueOf(id);
    }

    public static long convertIDToLong(String id) {
        return Long.parseLong(id);
    }

    public static boolean IsInvalidUserID(long id) {
        return isInvalidUserID(convertIDToString(id));
    }

    public static String convertGoodCase(String s) {
        String lower = s.toLowerCase();
        String res = lower.substring(1);
        res = Character.toUpperCase(s.charAt(0)) + res;
        return res;
    }

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

    public static int parseShelfString(String s) throws InvalidShelfException {
        if(s.length() != 1 || !Character.isDigit(s.charAt(0))) {
            throw new InvalidShelfException("Error: Invalid Shelf provided");
        }
        return s.charAt(0) - '0';
    }
}
