package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
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

        game.distributeInitCard();
        OutputView.revealInitCard(game.getUserInfoDtos(), game.getDealerRevealCards());

        game.playersGetMoreCard();
    }

    public void generatePerson() {
        OutputView.askUserNames();
        List<String> UserNames = InputView.getUserNames();

        for (String userName : UserNames) {
            game.addUser(new User(userName));
        }
    }
}
