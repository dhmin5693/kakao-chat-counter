package exception;

public class IllegalFilePathException extends RuntimeException {

    public IllegalFilePathException(String filePath) {
        super("파일 경로를 다시 확인해주세요: " + filePath);
    }
}
