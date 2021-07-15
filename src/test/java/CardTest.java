import blackjack.domain.Card;
import blackjack.domain.CardPack;
import blackjack.domain.CardShape;
import blackjack.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    @DisplayName("카드가 5/5 일때, 점수가 올바르게 계산되는지?")
    public void test_getTotalScore_with5and5() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));

        Assertions.assertEquals(10, CardPack.getTotalScore(cards));
    }

    @Test
    @DisplayName("카드가 10/10/10 일때, 점수가 올바르게 계산되는지?")
    public void test_getTotalScore_with10and10and10() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));

        Assertions.assertEquals(0, CardPack.getTotalScore(cards));
    }

    @Test
    @DisplayName("카드가 10/10/A 일때, 점수가 올바르게 계산되는지?")
    public void test_getTotalScore_with10and10andACE() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        Assertions.assertEquals(21, CardPack.getTotalScore(cards));
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 있을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    public void test_getMaxAddtionAceScore_ableCase() {
        int score = 10;
        int restScore = User.USER_LIMIT_CARD_VALUE - score;
        int maxScore = CardPack.getMaxAddtitionAceScore(restScore, 2);

        Assertions.assertEquals(9, maxScore);
    }

    @Test
    @DisplayName("에이스를 10으로 계산할 수 없을 때, 에이스의 갯수에 맞춰 추가 점수를 계산해내는지?")
    public void test_getMaxAdditionAceScore_unableCase() {
        int score = 22;
        int restScore = User.USER_LIMIT_CARD_VALUE - score;
        int maxScore = CardPack.getMaxAddtitionAceScore(restScore, 2);

        Assertions.assertEquals(0, maxScore);
    }

    @Test
    @DisplayName("에이스인 카드의 갯수를 올바르게 세아리는지?")
    public void test_getAceCount() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        cards.add(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        Assertions.assertEquals(1, CardPack.getAceCount(cards));
    }
}
