package domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

public enum FileType {

    TXT("txt", TxtFileAnalyser::new),
    CSV("csv", null),
    NONE("", null);

    private final String extension;
    private final Supplier<FileAnalyser> supplier;

    FileType(String extension, Supplier<FileAnalyser> supplier) {
        this.extension = extension;
        this.supplier = supplier;
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

    public FileAnalyser createFileAnalyser() {
        return this.supplier.get();
    }
}
