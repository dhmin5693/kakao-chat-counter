package rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {

    private static final SimpleDateFormat FORMAT =
        new SimpleDateFormat("yyyy/MM/dd");

    private static final String NO_INPUT_DATE = "-";

    static {
        FORMAT.setLenient(false);
    }

    public void validate(String date) {

        if (date == null) {
            throwException("null");
        }

        if (NO_INPUT_DATE.equals(date)) {
            return;
        }

        try {
            FORMAT.parse(date);
        } catch (ParseException e) {
            throwException(date);
        }
    }

    private void throwException(String data) {
        throw new IllegalArgumentException("날짜 형식이 잘못되었습니다:" + data);
    }
}
