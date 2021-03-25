package domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KakaoChatCounterTest {

    @DisplayName("랭킹 집계 정상")
    @MethodSource("tc01")
    @ParameterizedTest
    void test01(String filePath, String startDate, Ranking output) {
        var kakaoChatCounter = new KakaoChatCounter(filePath, startDate);

        assertEquals(output.toString(),
            kakaoChatCounter.extractRanking().toString());
    }

    private static Stream<Arguments> tc01() {
        // Todo fill test case data
        return null;
    }
}