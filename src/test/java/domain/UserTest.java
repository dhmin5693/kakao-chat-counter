package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private static final String CHAT_USER_NICKNAME = "USER";
    private static final String TEMPLATE = "#nickname# : #count#회";
    private static final int CHAT_COUNT = 3;

    @DisplayName("user 데이터 정상")
    @Test
    void success() {
        var user = User.builder()
                       .nickname(CHAT_USER_NICKNAME)
                       .count(CHAT_COUNT)
                       .build();

        var result = TEMPLATE.replace("#nickname#", CHAT_USER_NICKNAME)
                             .replace("#count#", String.valueOf(CHAT_COUNT));

        assertEquals(user.toString(), result);
    }

    @DisplayName("nickname이 없으면 exception")
    @Test
    void fail01() {
        assertThrows(IllegalArgumentException.class, () -> User.builder()
                                                               .count(CHAT_COUNT)
                                                               .build());
    }
}