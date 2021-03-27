package domain;

public class IllegalFilePathException extends RuntimeException {

    public IllegalFilePathException() {
        super();
    }

    public IllegalFilePathException(String filePath) {
        super("파일 경로를 다시 확인해주세요: " + filePath);
    }
}
