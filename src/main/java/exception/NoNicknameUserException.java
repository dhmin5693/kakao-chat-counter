package exception;

public class NoNicknameUserException extends RuntimeException {

    public NoNicknameUserException() {
        this("nickname은 필수값입니다.");
    }

    public NoNicknameUserException(String message) {
        super(message);
    }
}
