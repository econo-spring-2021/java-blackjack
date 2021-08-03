import blackjack.domain.Card;
import blackjack.domain.CardGrade;
import blackjack.domain.CardShape;
import blackjack.domain.OwnedCards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OwnedCardTest {

    @Test
    @DisplayName("소지한 카드들이 가질 수 있는 최선의 점수값을 올바르게 계산하는지?")
    void test_getScore() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        OwnedCards ownedCards = new OwnedCards(cards);

        Assertions.assertEquals(12, ownedCards.getScore());
    }

    @Test
    @DisplayName("소지한 카드들이 가질 수 있는 최소의 점수값을 올바르게 계산하는지?")
    void test_getCardsValueSume() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        OwnedCards ownedCards = new OwnedCards(cards);

        Assertions.assertEquals(2, ownedCards.getCardsValueSum());
    }

    @Test
    @DisplayName("소지한 카드들 중 에이스의 갯수를 올바르게 반환하는지?")
    void test_getAceCount() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        cards.add(new Card(CardShape.CLOVER, CardGrade.ACE));
        OwnedCards ownedCards = new OwnedCards(cards);

        Assertions.assertEquals(2, ownedCards.getAceCount());
    }
}
