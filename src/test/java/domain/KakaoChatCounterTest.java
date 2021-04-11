package domain;

import exception.IllegalFilePathException;
import exception.IllegalFileTypeException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KakaoChatCounterTest {

    @DisplayName("랭킹 집계 성공")
    @MethodSource("tc01")
    @ParameterizedTest
    void success(String filePath, String startDate, Ranking output) {
        var kakaoChatCounter = new KakaoChatCounter(filePath, startDate);

        var actual = kakaoChatCounter.extractRanking().toString();
        var expected = output.toString();

        assertEquals(actual, expected);
    }

    private static Stream<Arguments> tc01() {

        var windowSampleUsers = List.of(new User("kim", 2),
                                        new User("seok", 2),
                                        new User("user1", 1),
                                        new User("zoo", 2));

        var macSampleUsers = List.of(new User("park", 8),
                                     new User("lee", 4),
                                     new User("min", 3),
                                     new User("chan", 3),
                                     new User("kim", 3));

        return Stream.of(
            arguments("src/test/resources/windows-sample-01.txt", "-", new Ranking(windowSampleUsers)),
            arguments("src/test/resources/mac-sample-01.csv", "-", new Ranking(macSampleUsers))
        );
    }

    @DisplayName("파일 경로 오류")
    @ValueSource(strings = {"", "/a.txt", "/bb.csv"})
    @ParameterizedTest
    void fail01(String filePath) {

        String dontCare = "-";

        assertThrows(IllegalFilePathException.class, () -> {
            var counter = new KakaoChatCounter(filePath, dontCare);
            counter.extractRanking();
        });
    }

    @DisplayName("확장자 오류")
    @ValueSource(strings = {"/a.etc", "/abc.exe"})
    @ParameterizedTest
    void fail02(String filePath) {

        String dontCare = "-";

        assertThrows(IllegalFileTypeException.class, () -> {
            var counter = new KakaoChatCounter(filePath, dontCare);
            counter.extractRanking();
        });
    }
}