import java.util.Scanner;
import presentation.controller.KakaoChatCounterController;
import presentation.dto.KakaoChatCountRequestV1;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        System.out.println("파일 경로를 입력해주세요.");
        var filePath = scanner.nextLine();

        System.out.println("채팅 수를 세기 시작할 날짜를 정해주세요.");
        System.out.println("yyyy/MM/dd 형식으로 입력하되 불필요하면 입력하지 말고 엔터를 눌러주세요.");
        var startDate = scanner.nextLine();

        var controller = new KakaoChatCounterController();
        controller.countChat(KakaoChatCountRequestV1.builder()
                                                    .filePath(filePath)
                                                    .startDate(startDate)
                                                    .build());

//        KakaoChatCounter kakaoChatCounter = new KakaoChatCounter(filePath);
//        kakaoChatCounter.analyze();
//        kakaoChatCounter.printRanking();
    }
}
