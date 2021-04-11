package util;

public class StringUtils {
    public static boolean isBlank(String s) {
        if (s == null) {
            return true;
        }

        return "".equals(s.trim());
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
