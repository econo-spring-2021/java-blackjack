import blackjack.controller.GameController;
import blackjack.domain.Dealer;
import blackjack.domain.Game;
import blackjack.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameTest {

    GameController gameController;
    Game game;

    @BeforeEach
    void initGame() {
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
}
