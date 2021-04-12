package domain;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Ranking {

    private static final int INITIAL_RANK = 1;

    private final List<User> users;

    public Ranking(List<User> users) {
        this.users = users.stream()
                          .sorted()
                          .collect(toUnmodifiableList());
    }

    @Override
    public String toString() {

        var rankNumberCounter = new AtomicInteger(INITIAL_RANK);

        return users.stream()
                    .map(user -> rankNumberCounter.getAndIncrement() + "위 - " + user.toString())
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

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}
