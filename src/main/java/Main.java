import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("파일명을 입력해주세요.");
        String filename = scanner.nextLine();

        KakaoChatCounter kakaoChatCounter = new KakaoChatCounter(filename);
        kakaoChatCounter.analyze();
        kakaoChatCounter.printRanking();
    }
}
