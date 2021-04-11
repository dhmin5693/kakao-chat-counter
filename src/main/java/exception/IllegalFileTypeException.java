package exception;

public class IllegalFileTypeException extends RuntimeException {

    public IllegalFileTypeException(String filePath) {
        super("잘못된 파일입니다. 파일 경로를 다시 확인해주세요: " + filePath);
    }
}
