import blackjack.domain.Dealer;
import blackjack.domain.DealerInfoDto;
import blackjack.domain.Game;
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
        DealerInfoDto dealerInfoDto = dealer.getDealerInfoDto();

        Assertions.assertEquals(dealer.getName(), dealerInfoDto.getName());
        Assertions.assertEquals(dealer.getOwnedCards(), dealerInfoDto.getOwnedCards());
    }

    @Test
    @DisplayName("DealearRevealInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerRevealInfoDto() {
        game.distributeInitCard();
        DealerInfoDto dealerRevealInfoDto = dealer.getDealerRevealInfoDto();

        Assertions.assertEquals(dealer.getOwnedCards().getCard(0), dealerRevealInfoDto.getOwnedCards().getCard(0));
    }
}
