package presentation.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import presentation.dto.KakaoChatCountRequestV1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KakaoChatCounterControllerTest {

    private KakaoChatCounterController controller;

    @BeforeEach
    void setController() {
        controller = new KakaoChatCounterController();
    }

    @DisplayName("채팅 횟수를 정확히 세는데 성공함")
    @Test
    void success() {
        var request = KakaoChatCountRequestV1.builder()
                                             .build();

        var response = controller.countChat(request);
        var ranking = response.getRanking();
    }

    @DisplayName("파일의 경로가 잘못된 경우 exception 발생")
    @Test
    void fail01() {
        var request = KakaoChatCountRequestV1.builder()
                                             .build();

        assertThrows(Exception.class, () -> controller.countChat(request));
    }

    @DisplayName("시작일이 올바르게 입력되지 않은 경우 exception 발생")
    @Test
    void fail02() {
        var request = KakaoChatCountRequestV1.builder()
                                             .build();

        assertThrows(Exception.class, () -> controller.countChat(request));
    }
}