package util;

public class StringUtils {

    private static final String EMPTY_STRING = "";

    public static boolean isBlank(String s) {
        if (s == null) {
            return true;
        }

        return EMPTY_STRING.equals(s.trim());
    }
}
