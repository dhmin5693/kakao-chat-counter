package domain;

import exception.IllegalFilePathException;
import exception.IllegalFileTypeException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import util.StringUtils;

public class ChattingFileReader {

    private static final char DOT = '.';
    private static final int ADDITIONAL_INDEX = 1;

    private final String filePath;
    private final FileType fileType;

    public ChattingFileReader(String filePath) {
        this.filePath = filePath;

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalFilePathException(filePath);
        }

        String extension = getExtension(filePath);

        this.fileType = FileType.of(extension);
        if (fileType.isInvalidType()) {
            throw new IllegalFileTypeException(filePath);
        }
    }

    private String getExtension(String filePath) {
        int extensionIndex = filePath.lastIndexOf(DOT);
        return filePath.substring(extensionIndex + ADDITIONAL_INDEX);
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
