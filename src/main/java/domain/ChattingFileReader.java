package domain;

import exception.IllegalFilePathException;
import exception.IllegalFileTypeException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ChattingFileReader {

    private final String filePath;

    public ChattingFileReader(String filePath) {
        this.filePath = filePath;

        if (!(filePath.endsWith(".txt") && filePath.endsWith(".csv"))) {
            throw new IllegalFileTypeException(filePath);
        }
    }

    public Stream<String> getStreamData() {
        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalFilePathException(filePath);
        }
    }
}
