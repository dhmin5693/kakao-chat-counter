package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @DisplayName("null = true")
    @Test
    void test01() {
        assertTrue(StringUtils.isBlank(null));
    }

    @DisplayName("white space = true")
    @ValueSource(strings = {"", " ", "     "})
    @ParameterizedTest
    void test02(String whiteSpace) {
        assertTrue(StringUtils.isBlank(whiteSpace));
    }

    @DisplayName("has content = false")
    @ValueSource(strings = {"test", "123", "  5   "})
    @ParameterizedTest
    void test03(String whiteSpace) {
        assertFalse(StringUtils.isBlank(whiteSpace));
    }
}