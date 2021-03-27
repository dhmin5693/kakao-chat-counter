package rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {

    private static final SimpleDateFormat FORMAT =
        new SimpleDateFormat("yyyy/MM/dd");

    private static final String NO_INPUT_DATE = "";

    public void validate(String date) {

        if (NO_INPUT_DATE.equals(date)) {
            return;
        }

        try {
            FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
