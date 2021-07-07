import controller.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @DisplayName("setPlayerName 테스트")
    @Test
    public void setPlayerNameTest() throws IOException {
        String inputName = "asong,hero";
        assertThat(Game.setPlayerName(inputName).get(0).getName()).isEqualTo("asong");
        assertThat(Game.setPlayerName(inputName).get(1).getName()).isEqualTo("hero");
    }
}
