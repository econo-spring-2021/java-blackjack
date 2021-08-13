import blackjack.controller.GameController;
import blackjack.domain.*;
import blackjack.domain.dto.PlayerInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {

    GameController gameController;
    Game game;

    @BeforeEach
    void initGame() {
        CardPack.resetCardPack();
        game = new Game();
        gameController = new GameController(game);
    }

    @Test
    void test_distributeInitCard() {
        game.distributeInitCard();

        List<User> users = game.getUsers();
        for (User user : users) {
            Assertions.assertEquals(2, user.getOwnedCards().getCards().size());
        }

        Dealer dealer = game.getDealer();
        Assertions.assertEquals(2, dealer.getOwnedCards().getCards().size());
    }


    @Test
    @DisplayName("UserInfoDto 리스트가 올바르게 얻어지는지?")
    void test_getUserInfoDtos() {
        List<User> users = new ArrayList<>();
        users.add(new User("a"));
        users.add(new User("b"));
        game = new Game(users);

        game.distributeInitCard();

        Assertions.assertEquals(game.getUserInfoDtos(), game.getUsers());
    }

    @Test
    @DisplayName("DealearInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerInfoDto() {
        Dealer dealer = new Dealer();
        game = new Game(dealer);

        game.distributeInitCard();
        game.playersGetMoreCard();
        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();

        Assertions.assertTrue(dealerInfoDto.equals(dealer));
    }

    @Test
    @DisplayName("DealearRevealInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerRevealInfoDto() {
        Dealer dealer = new Dealer();
        game = new Game(dealer);

        game.distributeInitCard();
        PlayerInfoDto dealerRevealInfoDto = game.getDealerRevealInfoDto();

        Assertions.assertTrue(dealerRevealInfoDto.equals(dealer));
    }

    @Test
    @DisplayName("(Dealer:blackjack / Users:non-blackjack)의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerBlackjack_UserNonBlackjack() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeResult(true, false, 21);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(-1000, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(1000, dealerInfoDto.getIncome());

    }

    @Test
    @DisplayName("(Dealer:blackjack / User: blackjack)의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerBlackjack_UserBlackjack() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeBlackjack();
        a.judgeResult(true, false, 21);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(0, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(0, dealerInfoDto.getIncome());

    }

    @Test
    @DisplayName("(Dealer:non-blackjack / User: blackjack)의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerNonBlackjack_UserBlackjack() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeBlackjack();
        a.judgeResult(false, false, 20);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(1500, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(-1500, dealerInfoDto.getIncome());

    }

    @Test
    @DisplayName("(Dealer:burst / User: non-burst )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerBurst_UserNonBurst() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeResult(false, true, 30);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(1000, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(-1000, dealerInfoDto.getIncome());
    }

    @Test
    @DisplayName("(Dealer:burst / User: burst )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerBurst_UserBurst() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeBurst();
        a.judgeResult(false, true, 30);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(1000, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(-1000, dealerInfoDto.getIncome());
    }

    @Test
    @DisplayName("(Dealer:non-burst / User: burst )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_DealerNonBurst_UserBurst() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeBurst();
        a.judgeResult(false, false, 20);
        game.addUser(a);

        game.judgePlayersIncome();
        Assertions.assertEquals(-1000, a.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(1000, dealerInfoDto.getIncome());

    }

    @Test
    @DisplayName("(User1:win / User2:win )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_UsersWin() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.judgeResult(false, false, 17);
        game.addUser(a);

        User b = new User("b");
        b.setBet(2000);
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        b.judgeResult(false, false, 17);
        game.addUser(b);

        game.judgePlayersIncome();
        Assertions.assertEquals(1000, a.getIncome());
        Assertions.assertEquals(2000, b.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(-3000, dealerInfoDto.getIncome());
    }

    @Test
    @DisplayName("(User1:lose / User2:win )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_UsersWinAndLose() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TWO));
        a.judgeResult(false, false, 17);
        game.addUser(a);

        User b = new User("b");
        b.setBet(2000);
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        b.judgeResult(false, false, 17);
        game.addUser(b);

        game.judgePlayersIncome();
        Assertions.assertEquals(-1000, a.getIncome());
        Assertions.assertEquals(2000, b.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(-1000, dealerInfoDto.getIncome());
    }

    @Test
    @DisplayName("(User1:lose / User2:lose )의 상황에서 수익을 올바르게 계산하는가?")
    void test_calculatePlayersIncome_UsersLose() {
        User a = new User("a");
        a.setBet(1000);
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.TWO));
        a.judgeResult(false, false, 20);
        game.addUser(a);

        User b = new User("b");
        b.setBet(2000);
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TEN));
        b.addCard(new Card(CardShape.CLOVER, CardGrade.TWO));
        b.judgeResult(false, false, 20);
        game.addUser(b);

        game.judgePlayersIncome();
        Assertions.assertEquals(-1000, a.getIncome());
        Assertions.assertEquals(-2000, b.getIncome());

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        Assertions.assertEquals(3000, dealerInfoDto.getIncome());
    }
}
