package presentation.controller;

import domain.Ranking;
import domain.User;
import exception.IllegalFilePathException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import presentation.dto.KakaoChatCountRequest;
import rule.DateValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KakaoChatCounterControllerTest {

    private KakaoChatCounterController controller;

    @BeforeEach
    void setController() {
        controller = new KakaoChatCounterController(new DateValidator());
    }

    @DisplayName("채팅 횟수를 정확히 세는데 성공")
    @Test
    void success() {

        var expected =
            new Ranking(List.of(new User("kim", 2),
                                new User("seok", 4),
                                new User("zoo", 2),
                                new User("user1", 1)))
                .toString();

        var request = KakaoChatCountRequest.builder()
                                           .filePath("src/test/resources/windows-sample-01.txt")
                                           .startDate("-")
                                           .build();

        var response = controller.countChat(request);
        var actual = response.getRanking().toString();

        assertEquals(expected, actual);
    }

    @DisplayName("파일의 경로가 잘못된 경우 exception 발생")
    @Test
    void fail01() {

        var dontCare = "-";

        var request = KakaoChatCountRequest.builder()
                                           .filePath("/abc.txt")
                                           .startDate(dontCare)
                                           .build();

        assertThrows(IllegalFilePathException.class, () -> controller.countChat(request));
    }

    @DisplayName("시작일이 올바르게 입력되지 않은 경우 exception 발생")
    @ValueSource(strings = {"1", "5", "invalid-date"})
    @ParameterizedTest
    void fail02(String date) {

        var request = KakaoChatCountRequest.builder()
                                           .filePath("DONT-CATE")
                                           .startDate(date)
                                           .build();

        assertThrows(IllegalArgumentException.class, () -> controller.countChat(request));
    }
}