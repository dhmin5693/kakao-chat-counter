package domain;

import exception.NoNicknameUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private static final String CHAT_USER_NICKNAME = "USER";
    private static final int CHAT_COUNT = 3;
    private static final String TEMPLATE = "#nickname# : #count#회";

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
        assertThrows(NoNicknameUserException.class, () -> User.builder()
                                                              .count(CHAT_COUNT)
                                                              .build());
    }
}