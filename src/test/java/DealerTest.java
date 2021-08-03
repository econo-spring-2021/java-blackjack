import blackjack.domain.*;
import blackjack.domain.dto.DealerInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    Game game;
    Dealer dealer;

    @BeforeEach
    void initDealer() {
        dealer = new Dealer();
        game = new Game(dealer);
    }

    @Test
    @DisplayName("DealearInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerInfoDto() {
        game.distributeInitCard();
        game.playersGetMoreCard();
        DealerInfoDto dealerInfoDto = dealer.toDto();

        Assertions.assertEquals(dealer.getName(), dealerInfoDto.getName());
        Assertions.assertEquals(dealer.getOwnedCards(), dealerInfoDto.getOwnedCards());
    }

    @Test
    @DisplayName("DealearRevealInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerRevealInfoDto() {
        game.distributeInitCard();
        DealerInfoDto dealerRevealInfoDto = dealer.toRevealDto();

        Assertions.assertEquals(dealer.getOwnedCards().getCard(0), dealerRevealInfoDto.getOwnedCards().getCard(0));
    }

    @Test
    @DisplayName("블랙잭 판정이 올바르게 되는가?")
    void test_checkUserBlackjack() {
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));

        Assertions.assertTrue(dealer.getIsBlackjack());
    }

    @Test
    @DisplayName("딜러의 게임 전적을 올바르게 판단하는지?")
    void test_judgeDealerResult() {
        User a = new User("a");
        a.addCard(new Card(CardShape.CLOVER, CardGrade.KING));
        a.addCard(new Card(CardShape.CLOVER, CardGrade.KING));
        game.addUser(a);
        User b = new User("b");
        b.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));
        b.addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));
        game.addUser(b);

        game.getDealer().addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));
        game.getDealer().addCard(new Card(CardShape.CLOVER, CardGrade.FIVE));

        game.judgeUsersResult();
        game.judgeDealerResult(game.getUserInfoDtos());
        DealerInfoDto dealerInfoDto = game.getDealerInfoDto();

        Assertions.assertEquals(1, dealerInfoDto.getDrawCount());
        Assertions.assertEquals(1, dealerInfoDto.getLoseCount());
    }
}
