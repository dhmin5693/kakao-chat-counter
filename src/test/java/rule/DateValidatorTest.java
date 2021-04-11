package rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateValidatorTest {

    private DateValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DateValidator();
    }

    @DisplayName("yyyy/MM/dd 형식이며 날짜가 정확하거나 -이면 통과")
    @ValueSource(strings = {"-", "2020/10/31", "2021/04/11"})
    @ParameterizedTest
    void success(String date) {
        assertDoesNotThrow(() -> validator.validate(date));
    }

    @DisplayName("빈 문자열이 들어오면 throw")
    @ValueSource(strings = {"", " ", "                      "})
    @ParameterizedTest
    void fail01(String date) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(date));
    }

    @DisplayName("날짜 형식이 잘못된 경우 throw")
    @ValueSource(strings = {"2021-04-11", "2021 04 11"})
    @ParameterizedTest
    void fail02(String date) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(date));
    }

    @DisplayName("날짜 형식은 올바르나 올바르지 않은 날짜이면 throw")
    @ValueSource(strings = {"20123/03/50", "2021/31/31", "2021/02/50"})
    @ParameterizedTest
    void fail03(String date) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(date));
    }
}