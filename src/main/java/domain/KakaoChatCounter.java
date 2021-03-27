package domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static java.util.stream.Collectors.toList;

public class KakaoChatCounter {

    private final String filePath;
    private final String startDate;

    public KakaoChatCounter(String filePath, String startDate) {
        this.filePath = filePath;
        this.startDate = startDate;
    }

    // Todo Mac type extractor
    public Ranking extractRanking() {

        var cache = new HashMap<String, Integer>();

        try (BufferedReader br = readFile()) {

            skipToStartDate(br);

            String line;
            String nickname = "";

            while ((line = br.readLine()) != null) {
                int index = line.indexOf("] [");
                if (index > 0) {
                    nickname = line.substring(1, index);
                }

                if (!"".equals(nickname)) {
                    cache.put(nickname, cache.getOrDefault(nickname, 0) + 1);
                    cache.merge(nickname, 1, (value, count) -> count + 1);
                }
            }

            return new Ranking(cache.entrySet()
                                    .stream()
                                    .map(entry -> new User(entry.getKey(), entry.getValue()))
                                    .collect(toList())
            );

        } catch (IOException e) {
            throw new IllegalFilePathException(filePath);
        }
    }

    private boolean isNotNeedSkip() {
        // Todo implement this method
        return true;
    }

    private void skipToStartDate(BufferedReader br) throws IOException {

        if (isNotNeedSkip()) {
            return;
        }

        String line;

        while ((line = br.readLine()) != null) {
            if (line.equals("--------------- 2021년 1월 1일 금요일 ---------------")) {
                return;
            }
        }
    }

    private BufferedReader readFile() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }
}
