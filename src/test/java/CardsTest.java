import controller.Game;
import domain.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @DisplayName("setCards 테스트")
    @Test
    public void setCardsTest()  {
        Cards cards = new Cards(Cards.setCards());
        assertThat(cards.getCards().get(0).getCardName()).isEqualTo("2클로버");
    }
}
