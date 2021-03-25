package presentation.controller;

import java.nio.file.NoSuchFileException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import presentation.dto.KakaoChatCountRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KakaoChatCounterControllerTest {

    private KakaoChatCounterController controller;

    @BeforeEach
    void setController() {
        controller = new KakaoChatCounterController();
    }

    @DisplayName("채팅 횟수를 정확히 세는데 성공")
    @Test
    void success() {
        var request = KakaoChatCountRequest.builder()
                                           .filePath("resources/test-chat-01.txt")
                                           .build();

        var response = controller.countChat(request);
        var ranking = response.getRanking();

        var result = List.of("user1 : 3회", "user2 : 1회", "user3 : 1회");
        assertEquals(ranking.toString(), String.join("\n", result));
    }

    @DisplayName("파일의 경로가 잘못된 경우 exception 발생")
    @Test
    void fail01() {
        var request = KakaoChatCountRequest.builder()
                                           .filePath("invalid-filepath")
                                           .build();

        assertThrows(NoSuchFileException.class, () -> controller.countChat(request));
    }

    @DisplayName("시작일이 올바르게 입력되지 않은 경우 exception 발생")
    @ValueSource(strings = {"", "1", "5", "invalid-date"})
    @ParameterizedTest
    void fail02(String date) {

        var request = KakaoChatCountRequest.builder()
                                           .startDate(date)
                                           .build();

        assertThrows(IllegalArgumentException.class, () -> controller.countChat(request));
    }
}