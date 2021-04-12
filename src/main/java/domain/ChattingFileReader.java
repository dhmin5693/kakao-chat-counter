package domain;

import exception.IllegalFilePathException;
import exception.IllegalFileTypeException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import util.StringUtils;

public class ChattingFileReader {

    private final String filePath;
    private final FileType fileType;

    public ChattingFileReader(String filePath) {
        this.filePath = filePath;

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalFilePathException(filePath);
        }

        int extensionIndex = filePath.lastIndexOf('.');
        String extension = filePath.substring(extensionIndex + 1);

        this.fileType = FileType.of(extension);
        if (fileType.isInvalidType()) {
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

    public FileAnalyser createFileAnalyser() {
        return fileType.createFileAnalyser();
    }
}
