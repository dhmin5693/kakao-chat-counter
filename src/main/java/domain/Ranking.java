package domain;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Ranking {

    private final List<User> users;

    public Ranking(List<User> users) {
        this.users = users.stream()
                          .sorted()
                          .collect(toUnmodifiableList());
    }

    @Override
    public String toString() {
        return users.stream()
                    .map(User::toString)
                    .collect(joining("\n"));
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Ranking)) {
            return false;
        }

        Ranking ranking = (Ranking) o;
        return this.toString().equals(ranking.toString());
    }
}
