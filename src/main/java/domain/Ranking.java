package domain;

import java.util.List;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class Ranking {

    private final List<User> users;

    @Override
    public String toString() {
        return users.stream()
                    .map(User::toString)
                    .collect(joining("\n"));
    }
}
