import domain.Card;
import domain.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @DisplayName("getCardName 테스트")
    @Test
    public void getCardNameTest() {
        Card card = new Card(Card.Type.다이아몬드, "2");
        assertThat(card.getCardName()).isEqualTo("2다이아몬드");
    }

    @DisplayName("getNumber 테스트")
    @Test
    public void getNumberTest() {
        Card card = new Card(Card.Type.다이아몬드, "2");
        assertThat(card.getNumber()).isEqualTo("2");
    }
}
