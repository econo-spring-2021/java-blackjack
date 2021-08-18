import blackjack.domain.*;
import blackjack.domain.dto.PlayerInfoDto;
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
        PlayerInfoDto dealerInfoDto = new PlayerInfoDto(dealer);

        Assertions.assertEquals(dealer.getName(), dealerInfoDto.getName());
        Assertions.assertEquals(dealer.getOwnedCards(), dealerInfoDto.getOwnedCards());
    }

    @Test
    @DisplayName("DealearRevealInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerRevealInfoDto() {
        game.distributeInitCard();
        PlayerInfoDto dealerRevealInfoDto = dealer.toRevealDto();

        Assertions.assertEquals(dealer.getOwnedCards().getCard(0), dealerRevealInfoDto.getOwnedCards().getCard(0));
    }

    @Test
    @DisplayName("블랙잭 판정이 올바르게 되는가?")
    void test_judgeDealerBlackjack() {
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.ACE));
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        dealer.judgeBlackjack();

        Assertions.assertTrue(dealer.isBlackjack());
    }

    @DisplayName("버스트 판정이 올바르게 되는가?")
    void test_judgeDealerBurst() {
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        dealer.addCard(new Card(CardShape.CLOVER, CardGrade.JUMP));
        dealer.judgeBurst();

        Assertions.assertTrue(dealer.getIsBurst());
    }
}
