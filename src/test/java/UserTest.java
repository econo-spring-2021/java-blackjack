import blackjack.domain.Card;
import blackjack.domain.CardPack;
import blackjack.domain.CardShape;
import blackjack.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    User user;

    @BeforeEach
    public void initUser() {
        user = new User("test");
    }

    @Test
    @DisplayName("카드가 5/5 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    public void test_isPossibleToGetMoreCard_with5and5Card() {
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(4), CardPack.getCardValueFromIdx(4)));

        boolean rightJudgement = 5 + 5 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/10 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    public void test_isPossibleToGetMoreCard_with10and10and10Card() {
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));

        boolean rightJudgement = 10 + 10 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/ACE 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    public void test_isPossibleToGetMoreCard_with10and10andACECard() {
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(9), CardPack.getCardValueFromIdx(9)));
        user.addCard(new Card(CardShape.CLOVER, CardPack.getDelimeterFromIdx(0), CardPack.getCardValueFromIdx(0)));

        boolean rightJudgement = 5 + 5 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }
}
