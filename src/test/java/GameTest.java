import blackjack.controller.GameController;
import blackjack.domain.*;
import blackjack.domain.dto.DealerInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        DealerInfoDto dealerInfoDto = game.getDealerInfoDto();

        Assertions.assertEquals(dealerInfoDto, dealer);
    }

    @Test
    @DisplayName("DealearRevealInfoDto 가 올바르게 얻어지는지?")
    void test_getDealerRevealInfoDto() {
        Dealer dealer = new Dealer();
        game = new Game(dealer);

        game.distributeInitCard();
        DealerInfoDto dealerRevealInfoDto = game.getDealerRevealInfoDto();

        Assertions.assertEquals(dealerRevealInfoDto, dealer);
    }
}
