package blackjack.view;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public static List<String> parseNameString(String str) throws IllegalArgumentException {
        if (str.replaceAll(" ", "").equals(""))
            throw new IllegalArgumentException();

        String[] parsedNames = str.split(",");
        return Arrays.asList(parsedNames);
    }
}
