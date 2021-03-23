package domain;

import exception.NoNicknameUserException;
import lombok.Builder;

public class User {

    private final String nickname;
    private final int count;

    @Builder
    public User(String nickname, int count) {
        this.nickname = nickname;
        this.count = count;

        if (isBlankNickname()) {
            throw new NoNicknameUserException();
        }
    }

    @Override
    public String toString() {
        return nickname + " : " + count + "회";
    }

    private boolean isBlankNickname() {
        return nickname == null || nickname.trim()
                                           .isEmpty();
    }
}
