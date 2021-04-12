package domain;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvFileAnalyserTest {

    private final Stream<String> textStream =
        List.of("Date,User,Message",
                "2021-01-27 22:03:36,\"kim\",\"ㅋㅋㅋㅋㅋㅋ\"",
                "2021-01-27 22:03:36,\"kim\",\"넨\"",
                "2021-01-27 22:03:38,\"kim\",\"화이팅!!!\"",
                "2021-01-27 22:03:39,\"min\",\"그게 뭐죠,,\"",
                "2021-01-27 22:04:18,\"park\",\"php라니\"",
                "2021-01-27 22:04:19,\"이름없음\",\"레거시가 php인거죠 뭐ㅠㅜ저희회사도 스타텁인데 레거시가 php. . .\"",
                "2021-01-27 22:04:36,\"min\",\"내 미랜가\"",
                "2021-01-27 22:04:38,\"kim\",\"echo \"\"php\"\".\"\"최고\"\"\"",
                "2021-01-27 22:05:37,\"cheol\",\"못해도 용수철로 가즈아\"",
                "2021-01-27 22:06:36,\"이름없음\",\"용수철쓰는 회사가구싶어요\"",
                "2021-01-28 10:25:21,\"(알 수 없음)\",\"(알 수 없음)님이 나갔습니다.\"",
                "2021-01-28 11:33:19,\"algo\",\"강의 계획서 거의 올라왔네요\"",
                "2021-01-28 11:33:55,\"ve\",\"오\"",
                "2021-01-28 11:33:58,\"ve\",\"머들으실래요?\"",
                "2021-01-28 11:34:06,\"algo\",\"6전공이요\"",
                "2021-01-28 11:34:06,\"ve\",\"저 디공이여\"")
            .stream();

    @DisplayName("랭킹 집계 성공")
    @Test
    void success() {

        var expected = new Ranking(List.of(new User("algo", 2),
                                           new User("kim", 4),
                                           new User("min", 2),
                                           new User("ve", 3),
                                           new User("이름없음", 2),
                                           new User("cheol", 1),
                                           new User("park", 1)));

        var fileAnalyser = new CsvFileAnalyser();
        var actual = fileAnalyser.analyse(textStream);

        assertEquals(expected, actual);
    }

    @DisplayName("startDate 이전 데이터는 무시하고 랭킹 집계 성공")
    @Test
    void successWithSkip() {

        String startDate = "2021/01/28";

        var expected = new Ranking(List.of(new User("algo", 2),
                                           new User("ve", 3)));

        var fileAnalyser = new CsvFileAnalyser();
        var actual = fileAnalyser.analyse(textStream, startDate);

        assertEquals(expected, actual);
    }
}