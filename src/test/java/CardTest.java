import blackjack.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CardTest {

    OwnedCards ownedCards;

    @BeforeEach
    void init() {
        ownedCards = new OwnedCards();
    }

    @Test
    @DisplayName("카드의 갯수만큼 카드를 뽑을걍우, 모든 카드가 차례로 뽑히는지?")
    void test_getRandomCard() {
        CardPack cardPack = new CardPack();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < CardPack.CARD_SHAPE_COUNT * CardPack.CARD_VALUE_COUNT; i++) {
            cards.add(cardPack.getRandomCard());
        }

        // 서로 겹치는 카드는 없는가?
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                Assertions.assertEquals(false, cards.get(i).equals(cards.get(j)));
            }
        }

        // 카드 갯수가 정확한가?
        Assertions.assertEquals(CardPack.CARD_SHAPE_COUNT * CardPack.CARD_VALUE_COUNT, cards.size());
    }

    @Test
    @DisplayName("카드가 5/5 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with5and5() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[4]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[4]));

        Assertions.assertEquals(10, ownedCards.getScore());
    }

    @Test
    @DisplayName("카드가 10/10/10 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with10and10and10() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));

        Assertions.assertEquals(0, ownedCards.getScore());
    }

    @Test
    @DisplayName("카드가 10/10/A 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with10and10andACE() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[0]));

        Assertions.assertEquals(21, ownedCards.getScore());
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 있을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    void test_getMaxAdditionAceScore_ableCase() {
        // [7, ACE, ACE]
        int score = 10;
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[7]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[0]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[0]));

        int maxScore = ownedCards.getAdditionalAceScore(score);

        Assertions.assertEquals(9, maxScore);
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 없을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    void test_getMaxAdditionAceScore_unableCase() {
        // [10, 10, ACE]
        int score = 22;
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[1]));

        int maxScore = ownedCards.getAdditionalAceScore(score);

        Assertions.assertEquals(0, maxScore);
    }

    @Test
    @DisplayName("에이스인 카드의 갯수를 올바르게 세아리는지?")
    void test_getAceCount() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[9]));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardGrade.values()[0]));

        Assertions.assertEquals(1, ownedCards.getAceCount());
    }
}
