import blackjack.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    User user;

    @BeforeEach
    public void initUser() {
        user = new User("test");
    }

    @Test
    @DisplayName("블랙잭 판정이 올바르게 되는가?")
    void test_checkUserBlackjack() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));

        Assertions.assertTrue(user.getIsBlackjack());
    }

    @Test
    @DisplayName("카드가 5/5 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with5and5Card() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));

        final boolean rightJudgement = 5 + 5 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/10 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with10and10and10Card() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));

        final boolean rightJudgement = 10 + 10 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/ACE 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with10and10andACECard() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));

        final boolean rightJudgement = 5 + 5 < User.USER_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("유저의 이긴 게임의 결과를 올바르게 판단하는가?")
    void test_judgeResult_IfWin() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.judgeResult(20);

        Assertions.assertEquals(true, user.toDto().getIsWinner());
    }

    @Test
    @DisplayName("유저의 비긴 게임의 결과를 올바르게 판단하는가?")
    void test_judgeResult_IfDraw() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.judgeResult(21);

        Assertions.assertEquals(true, user.toDto().getIsDrawer());
    }
}
