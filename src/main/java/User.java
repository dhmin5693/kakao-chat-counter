import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class User {

    private final String nickname;
    private final int count;

    @Override
    public String toString() {
        return nickname + " : " + count + "회";
    }
}
