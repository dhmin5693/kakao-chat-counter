package domain;

import exception.IllegalFilePathException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ChattingFileReader {

    private final String filePath;

    public ChattingFileReader(String filePath) {
        this.filePath = filePath;
    }

    public Stream<String> getStreamData() {

        // do something...

        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalFilePathException(filePath);
        }
    }
}
