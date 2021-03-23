package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class KakaoChatCounter {

    private static final String EXTENSION = ".txt";
    private static final String ERR_FILE_NOT_FOUND = "파일을 찾을 수 없습니다.";
    private static final Pattern pattern = Pattern.compile("^\\[.+]\\s\\[.+]\\s.*$");

    private final File file;
    private Map<String, Integer> resultMap = new HashMap<>();

    public KakaoChatCounter(String fileName) {

        if (hasNotExtension(fileName)) {
            fileName = fileName + EXTENSION;
        }

        this.file = toFile(fileName);
    }

    private File toFile(String fileName) {

        try {
            URL res = getClass().getClassLoader().getResource(fileName);
            assert res != null;
            return Paths.get(res.toURI()).toFile();
        } catch (Exception e) {
            throw new IllegalArgumentException(ERR_FILE_NOT_FOUND);
        }
    }

    public void analyze() {
        try {
            FileReader textRead = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(textRead);
            String line;
            String nickname = "";

            boolean skip = true;
            while ((line = bfReader.readLine()) != null) {
//                Matcher matcher = pattern.matcher(line);
//
//                if (!matcher.find()) {
//                    continue;
//                }

                if (skip && line.equals("--------------- 2021년 1월 1일 금요일 ---------------")) {
                    skip = false;
                    continue;
                }

                if (skip) {
                    continue;
                }

                if (line.startsWith("---------------")) {
                    continue;
                }

                int index = line.indexOf("] [");
                if (index > 0) {
                    nickname = line.substring(1, index);
                }

                if (!"".equals(nickname)) {
                    resultMap.put(nickname, resultMap.getOrDefault(nickname, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasNotExtension(String fileName) {
        return !fileName.contains(EXTENSION);
    }

//    public void printRanking() {
//
//        List<String> ranking = resultMap.entrySet()
//                                        .stream()
//                                        .map(entry -> new User(entry.getKey(), entry.getValue()))
//                                        .sorted(Comparator.comparingInt(User::getCount)
//                                                          .reversed())
//                                        .map(User::toString)
//                                        .collect(Collectors.toList());
//
//        for (int i = 0; i < ranking.size(); i++) {
//            System.out.println(String.format("%-3s", i + 1) + "위 - " + ranking.get(i));
//        }
//    }
}
