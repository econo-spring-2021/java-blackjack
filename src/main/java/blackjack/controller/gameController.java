package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.CardPack;
import blackjack.domain.Game;
import blackjack.domain.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameController {
    Game game;

    public GameController() {
        this.game = new Game();
    }
    public GameController(Game game) {
        this.game = game;
    }

    public void play() {
        generatePerson();
        distributeInitCard();
    }

    public void generatePerson() {
        OutputView.askUserNames();
        List<String> UserNames = InputView.getUserNames();

        for (String userName : UserNames) {
            game.addUser(new User(userName));
        }
    }

    public void distributeInitCard() {
        for (int userIdx = 0; userIdx < game.getUsersCount(); userIdx++) {
            game.giveInitCardToUser(userIdx);
        }
        game.giveInitCardToDealer();
    }
}
