package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class GameController {
    Game game = new Game();

    public void play() {
        generatePerson();
    }

    public void generatePerson() {
        OutputView.askUserNames();
        List<String> UserNames = InputView.getUserNames();

        for (String userName : UserNames) {
            game.addUser(new User(userName));
        }
    }
}
