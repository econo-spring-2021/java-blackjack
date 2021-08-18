package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> getUserNames() {
        try {
            String str = scanner.nextLine();
            return parseNameString(str);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);

            return getUserNames();
        }
    }

    public static int getUserBet() {
        try {
            String str = scanner.nextLine();
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e);

            return getUserBet();
        }
    }

    public static List<String> parseNameString(String str) throws IllegalArgumentException {
        if (returnStringAfterRemovingSpace(str).equals(""))
            throw new IllegalArgumentException("빈 문자열");

        String[] parsedNames = str.split(",");
        return Arrays.asList(parsedNames);
    }

    public static String getYesOrNo() {
        try {
            String answer = returnStringAfterRemovingSpace(scanner.nextLine());
            if (!answer.equals("y") && !answer.equals("n")) {
                throw new IllegalArgumentException("올바르지 않은 입력입니다.");
            }

            return answer;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);

            return getYesOrNo();
        }
    }

    private static String returnStringAfterRemovingSpace(String str) {
        return str.replaceAll(" ", "");
    }
}
