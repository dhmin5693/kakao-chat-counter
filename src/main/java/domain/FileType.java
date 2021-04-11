package domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public enum FileType {

    TXT("txt"),
    CSV("csv"),
    NONE("");

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    private static final Map<String, FileType> CACHE =
        Arrays.stream(values())
              .collect(toMap(type -> type.extension.toLowerCase(Locale.ROOT),
                             Function.identity()));

    public static FileType of(String extension) {
        return CACHE.getOrDefault(extension.toLowerCase(Locale.ROOT), NONE);
    }

    public boolean isInvalidType() {
        return this == NONE;
    }
}
