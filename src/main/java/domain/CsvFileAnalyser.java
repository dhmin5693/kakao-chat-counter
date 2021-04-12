package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CsvFileAnalyser implements FileAnalyser {

    private static final int NICKNAME_START_INDEX = 21;

    private static final String TEXT_REGEX_PATTERN =
        "^[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2},\"(.*)\".\"(.*)\"$";

    private static final String SUFFIX_IN = "들어왔습니다.\"";
    private static final String SUFFIX_OUT = "나갔습니다.\"";

    private static final String NICKNAME_STARTING_POINT = "\",\"";

    @Override
    public Ranking analyse(Stream<String> stream) {

        Map<String, Integer> cache = new HashMap<>();

        stream.forEach(text -> {

            if (isNotChatting(text)) {
                return;
            }

            int nicknameEndIndex = text.indexOf(NICKNAME_STARTING_POINT);
            var nickname = text.substring(NICKNAME_START_INDEX, nicknameEndIndex);

            cache.merge(nickname, 1, Integer::sum);
        });

        return new Ranking(cache.entrySet()
                                .stream()
                                .map(entry -> new User(entry.getKey(), entry.getValue()))
                                .collect(toList()));
    }

    private boolean isNotChatting(String text) {
        return !text.matches(TEXT_REGEX_PATTERN)
            || text.endsWith(SUFFIX_IN)
            || text.endsWith(SUFFIX_OUT);
    }

    @Override
    public Ranking analyse(Stream<String> stream, String startDate) {
        return analyse(stream.dropWhile(text -> isStartDate(text, startDate)));
    }

    private boolean isStartDate(String text, String startDate) {
        if (isNotChatting(text)) {
            return true;
        }

        startDate = startDate.replaceAll("/", "-");
        return !text.contains(startDate);
    }
}
