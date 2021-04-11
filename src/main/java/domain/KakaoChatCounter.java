package domain;

import java.util.stream.Stream;

public class KakaoChatCounter {

    private final ChattingFileReader fileReader;
    private final String startDate;

    public KakaoChatCounter(String filePath, String startDate) {
        fileReader = new ChattingFileReader(filePath);
        this.startDate = startDate;
    }

    public Ranking extractRanking() {
        FileAnalyser analyser = fileReader.createFileAnalyser();
        Stream<String> stream = fileReader.getStreamData();
        return analyser.analyse(stream, startDate);
    }
}
