import blackjack.view.InputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InputTest {

    @Test
    void test_parseNameString_withProperString() {
        List<String> names = InputView.parseNameString("a,b,c");

        Assertions.assertEquals(Arrays.asList("a", "b", "c"), names);
    }

    @Test
    void test_parseNameString_withEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            InputView.parseNameString(" ");
        });
    }
}
