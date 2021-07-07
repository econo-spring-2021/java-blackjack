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
            System.out.println(e.getMessage());

            return getUserNames();
        }
    }

    public static List<String> parseNameString(String str) throws IllegalArgumentException {
        if (str.replaceAll(" ", "").equals(""))
            throw new IllegalArgumentException("빈 문자열");

        String[] parsedNames = str.split(",");
        return Arrays.asList(parsedNames);
    }
}
