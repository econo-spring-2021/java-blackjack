import blackjack.domain.CardPack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드의 갯수만큼 카드를 뽑을걍우, 모든 카드가 차례로 뽑히는지?")
    public void test_getRandomCard() {
        CardPack cardPack = new CardPack();
        for (int i = 0; i < CardPack.CARD_SHAPE_COUNT * CardPack.CARD_VALUE_COUNT; i++) {
            cardPack.getRandomCard();
        }

        for (int i = 0; i < CardPack.CARD_SHAPE_COUNT; i++) {
            for (int j = 0; j < CardPack.CARD_VALUE_COUNT; j++) {
                Assertions.assertTrue(cardPack.checkIsSelected(i, j));
            }
        }
    }
}
