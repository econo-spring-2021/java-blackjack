import blackjack.view.InputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InputTest {

    @Test
    @DisplayName("이름을 담긴 정상적인 문자열이 리스트로 변환되는지?")
    void test_parseNameString_withProperString() {
        List<String> names = InputView.parseNameString("a,b,c");

        Assertions.assertEquals(Arrays.asList("a", "b", "c"), names);
    }

    @Test
    @DisplayName("이름 문자열이 공백일 경우, 예외를 던지는지?")
    void test_parseNameString_withEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            InputView.parseNameString(" ")
        );
    }
}
