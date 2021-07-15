import blackjack.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        for (int i = 0; i < CardPack.CARD_SHAPE_COUNT * CardPack.CARD_VALUE_COUNT; i++) {
            cardPack.getRandomCard();
        }

        for (int i = 0; i < CardPack.CARD_SHAPE_COUNT; i++) {
            for (int j = 0; j < CardPack.CARD_VALUE_COUNT; j++) {
                Assertions.assertTrue(cardPack.checkIsSelected(i, j));
            }
        }
    }

    @Test
    @DisplayName("카드가 5/5 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with5and5() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));

        Assertions.assertEquals(10, ownedCards.getScore());
    }

    @Test
    @DisplayName("카드가 10/10/10 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with10and10and10() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));

        Assertions.assertEquals(0, ownedCards.getScore());
    }

    @Test
    @DisplayName("카드가 10/10/A 일때, 점수가 올바르게 계산되는지?")
    void test_getTotalScore_with10and10andACE() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        Assertions.assertEquals(21, ownedCards.getScore());
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 있을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    void test_getMaxAdditionAceScore_ableCase() {
        // [7, ACE, ACE]
        int score = 10;
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(7), CardPack.getCardValueFromIdx(7)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        int maxScore = ownedCards.getAddtitionAceScore(score);

        Assertions.assertEquals(9, maxScore);
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 없을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    void test_getMaxAdditionAceScore_unableCase() {
        // [10, 10, ACE]
        int score = 22;
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(1), CardPack.getCardValueFromIdx(1)));

        int maxScore = ownedCards.getAddtitionAceScore(score);

        Assertions.assertEquals(0, maxScore);
    }

    @Test
    @DisplayName("에이스인 카드의 갯수를 올바르게 세아리는지?")
    void test_getAceCount() {
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        ownedCards.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        Assertions.assertEquals(1, ownedCards.getAceCount());
    }
}
