package domain;

import java.util.stream.Stream;

public interface FileAnalyser {

    Ranking analyse(Stream<String> stream);
}
