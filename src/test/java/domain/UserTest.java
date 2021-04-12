package domain;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
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

    @DisplayName("user 정렬은 count 역순, count가 같을 시 이름 오름차순")
    @Test
    void sortTest() {

        var expected = List.of(
            new User("a", 6),
            new User("b", 5),
            new User("c", 5),
            new User("d", 4),
            new User("e", 3)
        );

        var users = Arrays.asList(
            expected.get(2),
            expected.get(1),
            expected.get(0),
            expected.get(4),
            expected.get(3)
        );

        var actual = users.stream()
                          .sorted()
                          .collect(toList());

        assertEquals(expected, actual);
    }

    @DisplayName("nickname이 없으면 exception")
    @Test
    void fail() {
        assertThrows(IllegalArgumentException.class, () -> User.builder()
                                                               .count(CHAT_COUNT)
                                                               .build());
    }
}