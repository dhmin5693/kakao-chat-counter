package domain;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChattingFileReaderTest {

    @DisplayName("파일 읽기 성공")
    @ValueSource(strings = {
        "src/test/resources/windows-sample-01.txt",
        "src/test/resources/mac-sample-01.csv"
    })
    @ParameterizedTest
    void success(String path) {
        assertDoesNotThrow(() -> new ChattingFileReader(path));
    }

    @DisplayName("path 오류")
    @ValueSource(strings = {
        "src/test/resources/windows-invalid-path.txt",
        "src/test/resources/mac-error-01.txt"
    })
    @ParameterizedTest
    void fail(String path) {
        assertThrows(FileNotFoundException.class, () -> new ChattingFileReader(path));
    }
}