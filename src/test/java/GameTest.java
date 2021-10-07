import controller.Game;
import domain.Card;
import domain.Cards;
import domain.Player;
import domain.Players;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private static Players players;

    @BeforeAll
    public static void setGame() throws IOException {
        players = new Players(Game.setPlayerName("asong,hero"));
    }

    @DisplayName("setPlayerName 테스트")
    @Test
    public void setPlayerNameTest() throws IOException {
        String inputName = "asong,hero";
        assertThat(Game.setPlayerName(inputName).get(0).getName()).isEqualTo("asong");
        assertThat(Game.setPlayerName(inputName).get(1).getName()).isEqualTo("hero");
    }
}
