package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TxtFileAnalyser implements FileAnalyser {

    private static final String FORMAT =
        "--------------- {yyyy}년 {MM}월 {dd}일 금요일 ---------------";

    private static final String TARGET_YEAR = "{yyyy}";
    private static final String TARGET_MONTH = "{MM}";
    private static final String TARGET_DAY = "{dd}";

    private static final String TEXT_REGEX_PATTERN =
        "^\\[(.+)]\\s\\[(오전|오후)\\s[0-9]{1,2}(:)[0-9]{1,2}]\\s(.*)$";

    private static final String END_OF_NICKNAME = "] [";

    private static final String NOT_SKIP = "-";

    private static final String DATE_REGEX_PATTERN =
        "^(-){15}\\s[0-9]{4}(년)\\s[0-9]{1,2}(월)\\s[0-9]{1,2}(일) (.)(요일)\\s(-){15}$";

    @Override
    public Ranking analyse(Stream<String> stream) {

        Map<String, Integer> cache = new HashMap<>();

        stream.forEach(text -> {

            if (!text.matches(TEXT_REGEX_PATTERN)) {
                return;
            }

            int index = text.indexOf(END_OF_NICKNAME);
            String nickname = text.substring(1, index);
            cache.merge(nickname, 1, Integer::sum);
        });

        return new Ranking(cache.entrySet()
                                .stream()
                                .map(entry -> new User(entry.getKey(), entry.getValue()))
                                .collect(toList()));
    }

    @Override
    public Ranking analyse(Stream<String> stream, String startDate) {
        return analyse(stream.dropWhile(text -> isStartDate(text, startDate)));
    }

    private boolean isStartDate(String text, String startDate) {

        if (startDate.equals(NOT_SKIP)) {
            return false;
        }

        if (!text.matches(DATE_REGEX_PATTERN)) {
            return true;
        }

        var year = Integer.parseInt(startDate.substring(0, 4));
        var month = Integer.parseInt(startDate.substring(5, 7));
        var day = Integer.parseInt(startDate.substring(8));

        var date = FORMAT.replace(TARGET_YEAR, String.valueOf(year))
                         .replace(TARGET_MONTH, String.valueOf(month))
                         .replace(TARGET_DAY, String.valueOf(day));

        return !date.equals(text);
    }
}
