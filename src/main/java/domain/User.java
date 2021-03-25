package domain;

import exception.NoNicknameUserException;
import lombok.Builder;

public class User implements Comparable<User> {

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

    private boolean isBlankNickname() {
        return nickname == null || nickname.trim()
                                           .isEmpty();
    }

    @Override
    public String toString() {
        return nickname + " : " + count + "회";
    }

    @Override
    public int compareTo(User o) {
        if (o.count != this.count) {
            return o.count - this.count;
        }

        return this.nickname.compareTo(o.nickname);
    }
}
