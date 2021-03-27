package domain;

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

        assertEquals(output.toString(),
                     kakaoChatCounter.extractRanking().toString());
    }

    private static Stream<Arguments> tc01() {
        return Stream.of(
            // todo set data
            arguments(),
            arguments(),
            arguments()
        );
    }

    @DisplayName("파일 경로 및 확장자 오류")
    @ValueSource(strings = {"", "/", "/abc.exe"})
    @ParameterizedTest
    void fail01(String filePath) {
        assertThrows(IllegalFilePathException.class, () -> {
            var counter = new KakaoChatCounter(filePath, "");
            counter.extractRanking();
        });
    }
}