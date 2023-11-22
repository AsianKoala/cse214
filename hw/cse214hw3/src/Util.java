public class Util {
    // MUST BE 13 DIGITS!
    public static boolean isValidISBN(String s) {
        if(s.length() > 13) return false;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidISBN(long s) {
        return isValidISBN(convertISBNToString(s));
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

    public static boolean isValidUserID(String s) {
        if(s.length() != 10) return false;
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String convertIDToString(long id) {
        StringBuilder str = new StringBuilder();
        str.append(id);
        return str.toString();
    }

    public static boolean isValidUserID(long id) {
        long tmp = id;
        int cnt = 0;
        while(tmp > 0) {
            cnt++;
            tmp /= 10;
        }
        return cnt == 10;
    }
}
