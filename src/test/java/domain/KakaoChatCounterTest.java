package domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KakaoChatCounterTest {

    @DisplayName("랭킹 집계 정상")
    @MethodSource("tc01")
    @ParameterizedTest
    void success(String filePath, String startDate, Ranking output) {
        var kakaoChatCounter = new KakaoChatCounter(filePath, startDate);

        assertEquals(output.toString(),
            kakaoChatCounter.extractRanking().toString());
    }

    private static Stream<Arguments> tc01() {
        // Todo fill test case data
        return null;
    }

    @DisplayName("파일 경로 및 확장자 오류")
    @ValueSource(strings = {"", "/", "/abc.exe"})
    @ParameterizedTest
    void fail01(String filePath) {
        assertThrows(IllegalArgumentException.class,
            () -> new KakaoChatCounter(filePath, ""));
    }

    @DisplayName("시작일 양식 오류")
    @ValueSource(strings = {"", "/", "/abc.exe"})
    @ParameterizedTest
    void fail02(String startDate) {
        assertThrows(IllegalArgumentException.class,
            () -> new KakaoChatCounter("resources/windows-test-chat-01.txt", startDate));
    }
}