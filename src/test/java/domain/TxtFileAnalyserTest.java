package domain;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TxtFileAnalyserTest {

    private final Stream<String> textStream =
        List.of("오픈톡방 이름 님과 카카오톡 대화",
                "저장한 날짜 : 2021-03-26 01:27:36",
                "",
                "--------------- 2021년 3월 25일 목요일 ---------------",
                "user1님이 들어왔습니다.운영정책을 위반한 메시지로 신고 접수 시 카카오톡 이용에 제한이 있을 수 있습니다. ",
                "[kim] [오후 4:03] 안녕하세요~",
                "[seok] [오후 4:03] 반갑습니다~",
                "[seok] [오후 4:03] 아이팟 프로도 나왔나요???",
                "[user1] [오후 4:04] 안녕하세요~",
                "[zoo] [오후 4:06] 죄송.. 에어팟프로 이야기한거예여. ㅋ",
                "[zoo] [오후 4:06] 공유 상품도 있어요~",
                "--------------- 2021년 3월 26일 금요일 ---------------",
                "[seok] [오후 4:06] 아~",
                "[seok] [오후 4:07] 애플이 에어팟도 다시 재개한줄 알았어요!",
                "[kim] [오후 4:07] 헉 그런가요!")
            .stream();

    @DisplayName("랭킹 집계 성공")
    @Test
    void success() {

        var expected = new Ranking(List.of(new User("kim", 2),
                                           new User("seok", 4),
                                           new User("user1", 1),
                                           new User("zoo", 2)));

        var fileAnalyser = new TxtFileAnalyser();
        var actual = fileAnalyser.analyse(textStream);

        assertEquals(expected, actual);
    }

    @DisplayName("startDate 이전 데이터는 무시하고 랭킹 집계 성공")
    @Test
    void successWithSkip() {

        String startDate = "2021/03/26";

        var expected = new Ranking(List.of(new User("seok", 2),
                                           new User("kim", 1)));

        var fileAnalyser = new TxtFileAnalyser();
        var actual = fileAnalyser.analyse(textStream, startDate);

        assertEquals(expected, actual);
    }
}