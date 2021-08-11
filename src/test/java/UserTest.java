import blackjack.domain.*;
import blackjack.domain.dto.UserInfoDto;
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
    void test_judgeUserBlackjack() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        user.judgeBlackjack();

        Assertions.assertTrue(user.getIsBlackjack());
    }

    @Test
    @DisplayName("카드가 5/5 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with5and5Card() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));

        final boolean rightJudgement = 5 + 5 < Player.Player_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/10 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with10and10and10Card() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));

        final boolean rightJudgement = 10 + 10 < Player.Player_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("카드가 10/10/ACE 인 경우, 유저의 카드 뽑는 판정이 옳은가")
    void test_isPossibleToGetMoreCard_with10and10andACECard() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));

        final boolean rightJudgement = 5 + 5 < Player.Player_LIMIT_CARD_VALUE;
        Assertions.assertEquals(rightJudgement, user.isPossibleToGetMoreCard());
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때, 유저도 블랙잭이면 게임의 결과를 올바르게 한단하는가")
    void test_judgeResultOnDealerBlackjack_IfUserBlackjack() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.judgeBlackjack();
        user.judgeResult(true, false, 21);

        Assertions.assertEquals(GameResult.DRAW, new UserInfoDto(user).getGameResult());
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때, 유저는 블랙잭이 아니면 게임의 결과를 올바르게 한단하는가")
    void test_judgeResultOnDealerBlackjack_IfUserNotBlackjack() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.judgeResult(true, false, 21);

        Assertions.assertEquals(GameResult.LOSE, new UserInfoDto(user).getGameResult());
    }

    @Test
    @DisplayName("딜러가 버스트일 때, 유저도 버스트면 게임의 결과를 올바르게 한단하는가")
    void test_judgeResultOnDealerBurst_IfUserBurst() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.judgeBurst();
        user.judgeResult(false, true, 30);

        Assertions.assertEquals(GameResult.WIN, new UserInfoDto(user).getGameResult());
    }

    @Test
    @DisplayName("딜러가 버스트일 때, 유저는 버스트가 아니면 게임의 결과를 올바르게 한단하는가")
    void test_judgeResultOnDealerBurst_IfUserNotBurst() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.judgeResult(false, true, 30);

        Assertions.assertEquals(GameResult.WIN, new UserInfoDto(user).getGameResult());
    }

    @Test
    @DisplayName("점수로 결과를 판단할 때, 유저의 이긴 게임의 결과를 올바르게 판단하는가?")
    void test_judgeResultByScore_IfWin() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.judgeResult(false, false, 20);

        Assertions.assertEquals(GameResult.WIN, new UserInfoDto(user).getGameResult());
    }

    @Test
    @DisplayName("점수로 결과를 판단할 때, 유저의 비긴 게임의 결과를 올바르게 판단하는가?")
    void test_judgeResultByScore_IfDraw() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        user.judgeResult(false, false ,21);

        Assertions.assertEquals(GameResult.DRAW, new UserInfoDto(user).getGameResult());
    }

    @DisplayName("버스트 판정이 올바르게 되는가?")
    void test_judgeUserBurst() {
        user.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        user.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        user.judgeBurst();

        Assertions.assertTrue(user.getIsBurst());
    }
}
