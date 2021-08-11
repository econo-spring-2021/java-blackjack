import domain.Card;
import domain.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private static Player player;

    @BeforeAll
    public static void setPlayer() {
        player = new Player("songa");
        player.getPlayerCards().getCardsList().add(new Card(Card.Type.클로버, "9"));
        player.getPlayerCards().getCardsList().add(new Card(Card.Type.클로버, "8"));
        int dealerScore = 5;
        //player setResult
        player.setResult(dealerScore);
    }

    @DisplayName("getName 테스트")
    @Test
    public void getNameTest() {
        assertThat(player.getName()).isEqualTo("songa");
    }

    @DisplayName("getResult 테스트")
    @Test
    public void resultTest() {
        assertThat(player.getResult()).isEqualTo("승");
    }

    @DisplayName("getDealerWinCount 테스트")
    @Test
    public void getDealerWinCountTest() {
        assertThat(player.getDealerWinCount()).isEqualTo(0);
    }
}
