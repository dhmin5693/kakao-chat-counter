package domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

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

            String line;
            String nickname = "";

            boolean skip = true;
            while ((line = br.readLine()) != null) {

                if (skip && line.equals("--------------- 2021년 1월 1일 금요일 ---------------")) {
                    skip = false;
                    continue;
                }

                if (skip || line.startsWith("---------------")) {
                    continue;
                }

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
                                    .sorted(this::compare)
                                    .map(entry -> new User(entry.getKey(), entry.getValue()))
                                    .collect(toList())
            );

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {

        int v1 = e1.getValue();
        int v2 = e2.getValue();

        if (v1 != v2) {
            return v2 - v1;
        }

        return e1.getKey().compareTo(e2.getKey());
    }

    private BufferedReader readFile() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    }
}
