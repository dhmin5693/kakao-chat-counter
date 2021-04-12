package domain;

import exception.IllegalFilePathException;
import exception.IllegalFileTypeException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    @DisplayName("정확한 file type 반환")
    @MethodSource(value = "fileTypeTestCase")
    @ParameterizedTest
    void fileTypeTest(String path, Class<?> expected) {

        var reader = new ChattingFileReader(path);
        var actual = reader.createFileAnalyser();

        assertThat(actual).isInstanceOf(expected);
    }

    private static Stream<Arguments> fileTypeTestCase() {
        return Stream.of(
            arguments("src/test/resources/windows-sample-01.txt", TxtFileAnalyser.class),
            arguments("src/test/resources/mac-sample-01.csv", CsvFileAnalyser.class)
        );
    }

    @DisplayName("path 오류")
    @ValueSource(strings = {
        "src/test/resources/windows-invalid-path.txt",
        "src/test/resources/mac-error-01.txt"
    })
    @ParameterizedTest
    void fail01(String path) {

        var exception = assertThrows(IllegalFilePathException.class, () -> {
            var fileReader = new ChattingFileReader(path);
            fileReader.getStreamData();
        });

        var actual = exception.getMessage();
        var expected = new IllegalFilePathException(path).getMessage();

        assertEquals(expected, actual);
    }


    @DisplayName("잘못된 file type 에러")
    @ValueSource(strings = {
        "src/test/resources/windows-invalid-path.tsv",
        "src/test/resources/mac-error-01.xlsx"
    })
    @ParameterizedTest
    void fail02(String path) {

        var exception = assertThrows(IllegalFileTypeException.class,
                                     () -> new ChattingFileReader(path));

        var actual = exception.getMessage();
        var expected = new IllegalFileTypeException(path).getMessage();

        assertEquals(expected, actual);
    }
}